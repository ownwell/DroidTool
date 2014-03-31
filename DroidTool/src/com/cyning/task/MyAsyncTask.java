package com.cyning.task;

import java.util.concurrent.atomic.AtomicBoolean;

import android.os.Handler;
import android.os.Message;

public abstract class MyAsyncTask<Params, Progress, Result> {
	private static final int MSG_POST = 1;
	private static final int MSG_PROGRESS = 2;

	private InternalHandler handler = new InternalHandler();
	private final AtomicBoolean mCancelled = new AtomicBoolean();
	private Status mStatus = Status.PENDING;
	private Params[] params;

	protected void onPreExecute() {
	}

	protected abstract Result doInBackground(Params... params);

	protected void onPostExecute(Result result) {
	}

	protected void onProgressUpdate(Progress... values) {
	}

	protected final void publishProgress(Progress... values) {
		if (!isCancelled()) {
			handler.obtainMessage(MSG_PROGRESS, new AsyncTaskResult<Progress>(this, values)).sendToTarget();
		}
	}

	public final void execute(Params... params) {
		if (mStatus != Status.PENDING) {
			switch (mStatus) {
			case RUNNING:
				throw new IllegalStateException("Cannot execute task:" + " the task is already running.");
			case FINISHED:
				throw new IllegalStateException("Cannot execute task:" + " the task has already been executed "
						+ "(a task can be executed only once)");
			default:
				break;
			}
		}

		mStatus = Status.RUNNING;

		onPreExecute();

		this.params = params;

		new Thread(new TaskRunnable()).start();
	}

	public final void executeSync(Params... params) {
		if (mStatus != Status.PENDING) {
			switch (mStatus) {
			case RUNNING:
				throw new IllegalStateException("Cannot execute task:" + " the task is already running.");
			case FINISHED:
				throw new IllegalStateException("Cannot execute task:" + " the task has already been executed "
						+ "(a task can be executed only once)");
			default:
				break;
			}
		}

		mStatus = Status.RUNNING;

		onPreExecute();

		this.params = params;

		postResult(doInBackground(params));
	}

	public final void cancel(boolean mayInterruptIfRunning) {
		mCancelled.set(true);
	}

	public final boolean isCancelled() {
		return mCancelled.get();
	}

	protected void onCancelled() {
	}

	protected void onCancelled(Result result) {
	}

	public final boolean isFinished() {
		return mStatus == Status.FINISHED;
	}

	private void finish(Result result) {
		mStatus = Status.FINISHED;
		if (isCancelled()) {
			onCancelled(result);
		} else {
			onPostExecute(result);
		}
	}

	private Result postResult(Result result) {
		@SuppressWarnings("unchecked")
		Message message = handler.obtainMessage(MSG_POST, new AsyncTaskResult<Result>(this, result));
		message.sendToTarget();
		return result;
	}

	public final Status getStatus() {
		return mStatus;
	}

	public enum Status {
		/**
		 * Indicates that the task has not been executed yet.
		 */
		PENDING,
		/**
		 * Indicates that the task is running.
		 */
		RUNNING,
		/**
		 * Indicates that {@link MyAsyncTask#onPostExecute} has finished.
		 */
		FINISHED,
	}

	private class TaskRunnable implements Runnable {
		@Override
		public void run() {
			postResult(doInBackground(params));
		}
	}

	static class InternalHandler extends Handler {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void handleMessage(Message msg) {
			AsyncTaskResult result = (AsyncTaskResult) msg.obj;
			switch (msg.what) {
			case MSG_POST:
				result.mTask.finish(result.mData[0]);
				break;
			case MSG_PROGRESS:
				result.mTask.onProgressUpdate(result.mData);
				break;
			default:
				break;
			}
		}
	}

	private static class AsyncTaskResult<Data> {
		@SuppressWarnings({ "rawtypes" })
		final MyAsyncTask mTask;
		final Data[] mData;

		@SuppressWarnings("rawtypes")
		AsyncTaskResult(MyAsyncTask task, Data... data) {
			mTask = task;
			mData = data;
		}
	}
}
