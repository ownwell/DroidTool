package com.cyning.android.adapter;

import java.util.List;

import android.view.LayoutInflater;
import android.widget.AbsListView;
import android.widget.ListAdapter;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-7-14
 *         可填充继承自AbsListView的列表型View的工具类。主要用于ListView，GridView等填充其子View。
 */
public class HolderViewFiller<T> {

	/** 从XML中创建对象 **/
	private LayoutInflater mInflater;

	/** View创建器 **/
	private ViewBuilderDelegate<T> mCreator;

	public HolderViewFiller(LayoutInflater inflater,
			ViewBuilderDelegate<T> creator) {
		mInflater = inflater;
		mCreator = creator;
	}

	/**
	 * 对目标View更新数据
	 * 
	 * @param targetView
	 *            目标View
	 * @param data
	 *            更新的数据
	 */
	public void update(AbsListView targetView, List<T> data) {
		HolderAdapter<T> holderAdapter = exportAdapter(targetView);
		if (null != holderAdapter) {
			holderAdapter.update(data);
		}
	}

	/**
	 * 向目标View添加数据
	 * 
	 * @param targetView
	 *            目标View
	 * @param set
	 *            数据
	 */
	public void add(AbsListView targetView, List<T> set) {
		HolderAdapter<T> holderAdapter = exportAdapter(targetView);
		if (null != holderAdapter) {
			holderAdapter.add(set);
		}
	}

	@SuppressWarnings("unchecked")
	public HolderAdapter<T> exportAdapter(AbsListView view) {
		ListAdapter adapter = view.getAdapter();
		HolderAdapter<T> holderAdapter = null;
		try {
			holderAdapter = (null == adapter) ? null
					: (HolderAdapter<T>) adapter;
			if (null == holderAdapter) {
				holderAdapter = new HolderAdapter<T>(mInflater, mCreator);
				view.setAdapter(holderAdapter);
			}
		} catch (ClassCastException ex) {
			throw new IllegalArgumentException(String.format(
					"Adapter in View(%s) is not a HolderAdapter!", view
							.getClass().getName()));
		}
		return holderAdapter;
	}
}
