package com.cyning.android.view;

import com.cyning.string.StringUtil;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.widget.Toast;

public class ToastManager {

	public static void showText(Context context, boolean isLong,
			CharSequence text) {
		Toast.makeText(context, text,
				isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static void showTextById(Context context, boolean isLong,
			int stringId) {
		String text = "";
		try {
			text = context.getResources().getString(stringId);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		if (!StringUtil.isEmpty(text)) {
			Toast.makeText(context, text,
					isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
		}
	}

	public static void showTextDefault(Context context, CharSequence text) {
		showText(context, false, text);
	}

	public static void showTextByIdDefault(Context context, int stringId) {
		showTextById(context, false, stringId);
	}
}
