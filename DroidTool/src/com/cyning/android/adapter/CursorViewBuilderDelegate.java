package com.cyning.android.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-10-15 游标适配器的View构建代理接口
 */
public interface CursorViewBuilderDelegate {

	/**
	 * 直接映射
	 * {@link android.widget.CursorAdapter#newView(android.content.Context, android.database.Cursor, android.view.ViewGroup)}
	 * 接口， 创建一个新View。
	 * 
	 * @param context
	 *            上下文
	 * @param cursor
	 *            指向当前数据的游标对象
	 * @param parent
	 *            父View
	 * @return 返回一个新View
	 */
	public View newView(Context context, Cursor cursor, ViewGroup parent);

	/**
	 * 直接映射
	 * {@link android.widget.CursorAdapter#bindView(android.view.View, android.content.Context, android.database.Cursor)}
	 * 接口， 将数据绑定到View中
	 * 
	 * @param currentView
	 *            当前View
	 * @param context
	 *            上下文
	 * @param cursor
	 *            指向当前数据的游标对象
	 */
	public void bindView(View currentView, Context context, Cursor cursor);

}
