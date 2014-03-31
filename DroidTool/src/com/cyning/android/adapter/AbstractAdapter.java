package com.cyning.android.adapter;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @author : 桥下一粒砂 chenyoca@gmail.com date : 2012-9-13 抽象Adapter类
 */
public abstract class AbstractAdapter<T> extends BaseAdapter {

	protected List<T> dataSetReference;
	protected LayoutInflater layoutInflater;
	protected ViewBuilderDelegate<T> viewBuilderDelegate;

	/**
	 * 创建Adapter，需要给定View创建接口。
	 * 
	 * @param inflater
	 *            LayoutInflater，{@link ViewBuilderDelegate}动态载入XML视图布局时，使用此引用。
	 * @param delegate
	 *            Adapter Cell视图的构建过程，由此接口实现。
	 */
	public AbstractAdapter(LayoutInflater inflater,
			ViewBuilderDelegate<T> delegate) {
		this.layoutInflater = inflater;
		this.viewBuilderDelegate = delegate;
	}

	/**
	 * 更新Adapter的数据集。
	 * 
	 * @param data
	 *            数据集
	 */
	public void update(List<T> data) {
		dataSetReference = data;
	}

	/**
	 * 清空Adapter的数据集
	 */
	public void clear() {
		if (dataSetReference != null) {
			dataSetReference.clear();
		}
	}

	/**
	 * 返回数据集引用
	 * 
	 * @return 数据集引用
	 */
	public List<T> getDataSet() {
		return dataSetReference;
	}

	/**
	 * 提交更新
	 */
	public void postChange() {
		notifyDataSetChanged();
	}

	/**
	 * 刷新
	 */
	public void postInvalidate() {
		notifyDataSetInvalidated();
	}

	/**
	 * 添加数据集，向数据缓存中添加多个元素。
	 * 
	 * @param set
	 *            元素集
	 */
	public void add(List<T> set) {
		if (null == dataSetReference) {
			throw new NullPointerException(
					"DataSet is NULL, makeCall 'update' first !");
		}
		dataSetReference.addAll(set);
	}

	/**
	 * 添加数据，向数据缓存中添加一个元素。
	 * 
	 * @param item
	 *            元素
	 */
	public void add(T item) {
		if (null == dataSetReference) {
			throw new NullPointerException(
					"DataSet is NULL, makeCall 'update' first !");
		}
		dataSetReference.add(item);
	}

	/**
	 * 删除数据集中指定位置的数据。
	 * 
	 * @param position
	 *            要删除的数据在数据集中的位置
	 */
	public void remove(int position) {
		if (dataSetReference == null)
			return;
		dataSetReference.remove(position);
	}

	@Override
	public int getCount() {
		return null == dataSetReference ? 0 : dataSetReference.size();
	}

	@Override
	public T getItem(int position) {
		return null == dataSetReference ? null : dataSetReference.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
