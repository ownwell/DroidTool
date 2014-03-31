package com.cyning.android.view;

import android.app.Activity;
import android.view.View;

public abstract class ViewController {

	protected Activity context;
	protected View contentView;

	public ViewController(Activity context) {
		this.context = context;
	}

	public void setupContentView(View view) {
		contentView = view;
		onBindingView(contentView);
	}

	public abstract void onBindingView(View contentView);

	public View getContentView() {
		return this.contentView;
	}

}
