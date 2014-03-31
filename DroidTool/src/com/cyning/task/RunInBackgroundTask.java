package com.cyning.task;


public class RunInBackgroundTask extends MyAsyncTask<Void, Void, Void> {
	
	private RunCallBack runCallBack;
	private AsyncTaskListener asyncTaskListener;
	
	public interface RunCallBack {
		void run();
	}

	@Override
	protected Void doInBackground(Void... params) {
		if(runCallBack != null)
			runCallBack.run();
		return null;
	}

	public RunCallBack getRunCallBack() {
		return runCallBack;
	}

	public void setRunCallBack(RunCallBack runCallBack) {
		this.runCallBack = runCallBack;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if(asyncTaskListener != null){
			asyncTaskListener.onPostExecute(result);
		}
		runCallBack = null;
		asyncTaskListener = null;
	}

	public AsyncTaskListener getAsyncTaskListener() {
		return asyncTaskListener;
	}

	public void setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
		this.asyncTaskListener = asyncTaskListener;
	}


}
