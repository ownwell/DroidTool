package com.cyning.android.base;

import java.util.Stack;

import android.app.Activity;

public class ActivityPool {

	private static ActivityPool mInstance = null;

	/** activity pool */
	private Stack<Activity> activityStack;

	private ActivityPool() {
		activityStack = new Stack<Activity>();
	}

	public synchronized static ActivityPool getInstance() {
		 
			if (mInstance == null) {
				mInstance = new ActivityPool();
			}
		return mInstance;

	}

	/**
	 * 退出栈顶Activity
	 * 
	 * @param activity
	 */
	public void removeActivity(Activity activity) {
		if (!activityStack.empty() && activity != null) {
			// 在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 删除某一类的Activity
	 * 
	 * @param mActivity
	 */
	public void removeAllActivities(Activity mActivity) {
		boolean flag = true;
		if (mActivity == null)
			flag = false;
		while (!activityStack.isEmpty() && flag) {
			Activity topActivity = getTopActivity();
			flag = (topActivity == null?false:topActivity.getClass().equals(mActivity.getClass()));
			if (flag)
				removeActivity(topActivity);
			
		}
	}

	/**
	 * 获得当前栈顶Activity
	 * 
	 * @return
	 */
	public Activity getTopActivity() {
		Activity activity = null;
		if (!activityStack.empty())
			activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 将当前Activity推入栈中
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 退出栈中所有Activity 除了cls
	 * 
	 * @param cls
	 */
	public void removeAllActivityExceptOne(Class<? extends Activity> cls) {
		boolean flag = true;
		while (flag) {
			Activity activity = getTopActivity();
			flag = (activity == null?false:!activity.getClass().equals(cls));
			
			if (flag)
				removeActivity(activity);
		}
	}

	/**
	 * 删除所有的activity
	 */
	public void removeAllActivity() {
		if (activityStack != null && !activityStack.isEmpty()) {
			Activity activity = getTopActivity();
			removeActivity(activity);
		}
	}

	public Stack<Activity> getActivityPool() {
		return activityStack;
	}

}
