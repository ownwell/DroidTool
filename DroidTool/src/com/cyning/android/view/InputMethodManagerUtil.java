package com.cyning.android.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodManagerUtil {
	
	public static boolean isOpenInputMethodManager(Context context){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen=imm.isActive(); 
		return isOpen;
	}
	public static void openInputMethodManager(Context context,View v){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); 
		//(提供当前操作的标记，SHOW_FORCED表示强制显示)
		imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);
	}
	public static void openInputMethodManager(final Context context,View v,int delay){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				// imm.showSoftInput(, InputMethodManager.RESULT_SHOWN);
				// if (getCurrentFocus() != null) {
				// imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(),
				// 0);
				// }
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			}
		}, delay);
	}
	public static void closeInputMethodManager(Context context,View v){
		InputMethodManager imm = ((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE));
		imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  

	}
	

}
