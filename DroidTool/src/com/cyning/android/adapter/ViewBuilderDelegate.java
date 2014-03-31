package com.cyning.android.adapter;

import android.view.LayoutInflater;
import android.view.View;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-7-14 View 构建器代理接口
 */
public interface ViewBuilderDelegate<E> {

	/**
	 * 创建View,HolderAdapter需要创建View时，会调用此方法创建View。
	 * 
	 * @param layoutInflater
	 *            LayoutInflater，用于从XML布局文件中创建View。
	 * @param data
	 *            数据。
	 * @return 返回创建的新View。
	 */
	View newView(LayoutInflater layoutInflater, E data);

	/**
	 * 当View被使用时，将数据绑定（更新）到View中。在{@link #newView(android.view.LayoutInflater,E
	 * data)}方法执行后， 会自动调用此方法一次
	 * 
	 * @param view
	 *            需要进行数据绑定的View
	 * @param position
	 *            当前数据在数据集中的位置
	 * @param data
	 *            数据
	 */
	void bindView(View view, int position, E data);

	/**
	 * 此方法在{@link #bindView(android.view.View, int, Object)}之前调用。
	 * 
	 * @param view
	 *            当前View对象
	 * @param data
	 *            当前View绑定的数据
	 */
	void releaseView(View view, E data);
}