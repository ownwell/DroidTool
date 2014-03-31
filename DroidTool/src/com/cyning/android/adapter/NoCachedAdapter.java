package com.cyning.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-12-3 不使用View缓存的Adapter。每次
 *         {@link #getView(int, android.view.View, android.view.ViewGroup)}
 *         请求获取View，如果都从 {@link
 *         ViewBuilderDelegate#newView(android.view.LayoutInflater, T data)}
 *         接口中创建View。
 */
public class NoCachedAdapter<T> extends AbstractAdapter<T> {

	public NoCachedAdapter(LayoutInflater inflater,
			ViewBuilderDelegate<T> delegate) {
		super(inflater, delegate);
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		if (null == convertView) {
			T data = getItem(pos);
			convertView = viewBuilderDelegate.newView(layoutInflater, data);
			viewBuilderDelegate.bindView(convertView, pos, data);
		}
		return convertView;
	}
}
