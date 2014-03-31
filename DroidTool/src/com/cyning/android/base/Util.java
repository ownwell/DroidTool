package com.cyning.android.base;

import android.app.Activity;
import android.view.View;

public class Util {
	
	@SuppressWarnings("unchecked")
	public static <T extends View > T  v(Activity act,int viewID){
		
		return (T) act.findViewById(viewID);
		
	}

}
