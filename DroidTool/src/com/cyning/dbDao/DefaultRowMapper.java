package com.cyning.dbDao;

import android.database.Cursor;

/**
 * 通过cursor和列名得到相关的值
 * 
 * @author cyning
 * 
 * @param <T>
 */
public abstract class DefaultRowMapper<T> implements RowMapper<T> {

	public String getString(Cursor cursor, String col) {
		return cursor.getString(cursor.getColumnIndex(col));
	}

	public int getInt(Cursor cursor, String col) {
		return cursor.getInt(cursor.getColumnIndex(col));
	}

	public byte[] getBlob(Cursor cursor, String col) {
		return cursor.getBlob(cursor.getColumnIndex(col));
	}

	public Double getDouble(Cursor cursor, String col) {
		return cursor.getDouble(cursor.getColumnIndex(col));
	}

	public float getFloat(Cursor cursor, String col) {
		return cursor.getFloat(cursor.getColumnIndex(col));
	}

	public long getLong(Cursor cursor, String col) {
		return cursor.getLong(cursor.getColumnIndex(col));
	}

	public short getShort(Cursor cursor, String col) {
		return cursor.getShort(cursor.getColumnIndex(col));
	}
}
