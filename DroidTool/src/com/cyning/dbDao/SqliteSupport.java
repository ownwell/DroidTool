package com.cyning.dbDao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

public class SqliteSupport {

	/**
	 * 是否存在
	 * 
	 * @param cursor
	 * @return
	 * @throws SQLiteException
	 */
	public boolean exist(Cursor cursor) throws SQLiteException {
		try {
			return cursor.moveToNext();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/**
	 * 得到相关row的值
	 * 
	 * @param cursor
	 * @param mapper
	 * @return
	 * @throws SQLiteException
	 */
	public <T> T get(Cursor cursor, final RowMapper<T> mapper)
			throws SQLiteException {
		try {
			if (cursor.moveToNext()) {
				return mapper.mapRow(cursor);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return null;
	}

	/**
	 * 查询得到相关相关row的值
	 * 
	 * @param l
	 * @param cursor
	 * @param mapper
	 * @throws SQLiteException
	 */
	public <T> void query(List<T> l, Cursor cursor, final RowMapper<T> mapper)
			throws SQLiteException {
		try {
			while (cursor.moveToNext()) {
				l.add(mapper.mapRow(cursor));
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	public <T> List<T> query(Cursor cursor, final RowMapper<T> mapper)
			throws SQLiteException {
		List<T> l = new ArrayList<T>();
		query(l, cursor, mapper);
		return l;
	}

	public void query(Cursor cursor, RowCallbackHandler handler)
			throws SQLiteException {
		try {
			while (cursor.moveToNext()) {
				handler.processRow(cursor);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	public <T> T query(Cursor cursor,
			final ScrollableResultsExtractor<T> extractor)
			throws SQLiteException {
		try {
			return extractor.extractData(cursor);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	public long queryForLong(Cursor cursor) throws SQLiteException {
		try {
			if (cursor.moveToNext()) {
				return cursor.getLong(0);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return 0;
	}

	public int queryForInt(Cursor cursor) throws SQLiteException {
		try {
			if (cursor.moveToNext()) {
				return cursor.getInt(0);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return 0;
	}

	public short queryForShort(Cursor cursor) throws SQLiteException {
		try {
			if (cursor.moveToNext()) {
				return cursor.getShort(0);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return 0;
	}

	public float queryForFloat(Cursor cursor) throws SQLiteException {
		try {
			if (cursor.moveToNext()) {
				return cursor.getFloat(0);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return 0;
	}

	public String queryForString(Cursor cursor) throws SQLiteException {
		try {
			if (cursor.moveToNext()) {
				return cursor.getString(0);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return null;
	}
}
