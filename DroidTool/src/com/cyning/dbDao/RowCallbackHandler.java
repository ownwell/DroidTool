package com.cyning.dbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

public interface RowCallbackHandler {
	void processRow(Cursor cursor) throws SQLiteException;
}
