package com.cyning.android.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-10-15 游标适配器
 */
public class SimpleCursorAdapter extends CursorAdapter {

	private CursorViewBuilderDelegate viewBuilderDelegate;

	public SimpleCursorAdapter(Context context, Cursor c, boolean autoRequery,
			CursorViewBuilderDelegate viewBuilderDelegate) {
		super(context, c, autoRequery);
		this.viewBuilderDelegate = viewBuilderDelegate;
	}

	public SimpleCursorAdapter(Context context, Cursor c,
			CursorViewBuilderDelegate viewBuilderDelegate) {
		this(context, c, true, viewBuilderDelegate);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return viewBuilderDelegate.newView(context, cursor, parent);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		if (cursor == null)
			return;
		viewBuilderDelegate.bindView(view, context, cursor);
	}

}
