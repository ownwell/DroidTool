package com.cyning.task;

public interface AsyncTaskListener {
	void onPreExecute();
	void onPostExecute(Object obj);
	void onUpdateProgress(Object obj);
}
