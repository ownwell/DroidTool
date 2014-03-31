package com.cyning.android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-9-13
 *         使用convertView作View缓存的Adapter实现。 Convert View的相关知识，可参考
 *         http://www.cnblogs.com/over140/archive/2011/03/23/1991100.html
 */
public class ConvertViewAdapter<T> extends AbstractAdapter<T> {

	/**
	 * @see AbstractAdapter#AbstractAdapter(android.view.LayoutInflater,
	 *      ViewBuilderDelegate)
	 */
	public ConvertViewAdapter(LayoutInflater inflater,
			ViewBuilderDelegate<T> delegate) {
		super(inflater, delegate);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		T data = dataSetReference.get(position);
		if (null == convertView) {
			convertView = viewBuilderDelegate.newView(layoutInflater, data);
		}
		viewBuilderDelegate.bindView(convertView, position, data);
		return convertView;
	}

}
