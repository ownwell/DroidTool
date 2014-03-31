package com.cyning.android.base;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class BaseFragmentActivity extends FragmentActivity {

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
