package com.cyning.android.base;

import java.lang.Thread.UncaughtExceptionHandler;

import com.cyning.android.system.StrictModeUtil;
import com.lurencun.android.toolkit.BuildConfig;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.webkit.CookieSyncManager;

public class BaseApplication extends Application {

	private ActivityPool activityPool = null;

	protected void setUncaughtException() {

		// 以下用来捕获程序崩溃异常
		Intent intent = new Intent();
		// 参数1：包名，参数2：程序入口的activity
		intent.setClassName(GlobalConfigure.packageName,
				GlobalConfigure.reStartActivity);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		restartIntent = PendingIntent.getActivity(getApplicationContext(), 0,
				intent, Intent.FLAG_ACTIVITY_NEW_TASK);

		Thread.setDefaultUncaughtExceptionHandler(restartHandler); // 程序崩溃时触发线程
	}

	PendingIntent restartIntent;
	public UncaughtExceptionHandler restartHandler = new UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
			mgr.set(AlarmManager.RTC, System.currentTimeMillis() + GlobalConfigure.RESTARTDELAY,
					restartIntent); // 1秒钟后重启应用
			onExit();
		}
	};

	@Override
	public void onCreate() {
		super.onCreate();
		activityPool = ActivityPool.getInstance();
		if (BuildConfig.DEBUG) {
			StrictModeUtil.enableStrictMode();
		}
		CookieSyncManager.createInstance(getApplicationContext());
		
		            CookieSyncManager.createInstance(this.getApplicationContext());
	}

	public void addActivity(Activity activity) {
		activityPool.addActivity(activity);
	}

	public void removeActivity(Activity activity) {
		activityPool.removeActivity(activity);
	}

	public void removeAllActivity() {
		activityPool.removeAllActivity();
	}

	public void onExit() {
		activityPool.removeAllActivity();
	}

	public ActivityPool getActivityPool() {
		return activityPool;
	}

}
