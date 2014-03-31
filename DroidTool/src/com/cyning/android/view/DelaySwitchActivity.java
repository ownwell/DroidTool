package com.cyning.android.view;

import android.app.Activity;
import android.os.Handler;

import com.cyning.android.common.Params;
import com.cyning.android.system.ActivityUtility;

/**
 * @author : 桥下一粒砂
 * @email : chenyoca@gmail.com
 * @date : 2012-12-19
 * @desc : 延时切换Activity
 */
public abstract class DelaySwitchActivity extends Activity {

	/**
	 *切换Activity的线程
	 */
	private Runnable switchCallback;
	/**
	 * 监听切换线程的Handler
	 */
	private Handler switchHandler;
	/**
	 * 默认的等待时间
	 */
	private int splashDelay = 3 * 1000;
	/**
	 * 下一个Activity
	 */
	private Class<? extends Activity> nextActivity;
	/**
	 * 传递的参数
	 */
	private Params params;

	{
		switchCallback = new Runnable() {
			@Override
			public void run() {
				switchToNextView();
			}
		};
		switchHandler = new Handler();
	}

	final protected void setSplashDelay(int delayMillis) {
		splashDelay = delayMillis;
	}

	final protected void setNextActivity(Class<? extends Activity> target) {
		nextActivity = target;
	}

	final protected void setParams(Params params) {
		this.params = params;
	}

	final protected void removeAction() {
		switchHandler.removeCallbacks(switchCallback);
	}

	final protected void switchToNextView() {
		if (nextActivity != null) {
			ActivityUtility.switchTo(this, nextActivity, params);
		}
		removeAction();
		finish();
	}

	@Override
	final protected void onResume() {
		super.onResume();
		removeAction();
		switchHandler.postDelayed(switchCallback, splashDelay);
		onResumeEx();
	}

	protected void onResumeEx() {
	}
}
