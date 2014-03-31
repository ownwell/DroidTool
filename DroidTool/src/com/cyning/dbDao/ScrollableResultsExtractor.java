package com.cyning.dbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

public interface ScrollableResultsExtractor<T> {
	T extractData(Cursor cursor) throws SQLiteException;
}
