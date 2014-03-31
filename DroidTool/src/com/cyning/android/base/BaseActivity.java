package com.cyning.android.base;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity {

	BaseApplication app ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	  
		
		app = (BaseApplication) getApplication();
		app.addActivity(this);
		
	}
	
	public <T extends View> T view(int id) {
	
		return Util.v(this, id);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		app.removeActivity(this);
	}

	
}
