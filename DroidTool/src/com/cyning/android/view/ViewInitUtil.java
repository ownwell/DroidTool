package com.cyning.android.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ViewInitUtil {

	public static <T extends View> T v(View v, int resId) {
		return (T) v.findViewById(resId);
	}

	public static <T extends View> T v(Activity activity, int resId) {
		return (T) activity.findViewById(resId);
	}

	public <T extends View> T create(Context context, int layoutId) {
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		return (T) layoutInflater.inflate(layoutId, null, false);
	}

}
