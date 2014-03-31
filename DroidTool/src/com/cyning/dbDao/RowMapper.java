package com.cyning.dbDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;

/**
 * 给一个cursor得到其值
 * 
 * @author cyning
 * 
 * @param <T>
 *            返回的值的类型，泛型
 */
public interface RowMapper<T> {

	T mapRow(Cursor cursor) throws SQLiteException;
}
