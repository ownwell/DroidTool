package com.cyning.android.database;

import android.database.Cursor;
import android.widget.CursorAdapter;

/**
 * @author 桥下一粒砂 (chenyoca@gmail.com) date 2013-4-7 异步查询数据库
 */
public class AsynchronousCursorQueryTask extends AsynchronousQueryTask {

	private CursorAdapter adapter;

	public AsynchronousCursorQueryTask(CursorAdapter adapter,
			CursorQueryDelegate delegate) {
		super(adapter.getCursor(), delegate);
		this.adapter = adapter;
	}

	@Override
	protected void changeCursor(Cursor newCursor) {
		adapter.changeCursor(newCursor);
	}
}