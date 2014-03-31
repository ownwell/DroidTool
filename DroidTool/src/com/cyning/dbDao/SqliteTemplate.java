package com.cyning.dbDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public abstract class SqliteTemplate extends SqliteSupport {

	public long insert(final String table, final String nullColumnHack,
			final ContentValues values) {
		return execute2(true, new DatabaseProcessor2<Long>() {
			@Override
			public Long process(SQLiteDatabase db) throws SQLiteException {
				return db.insert(table, nullColumnHack, values);
			}
		});
	}

	public int delete(final String table, final String whereClause,
			final String[] whereArgs) {
		return execute2(true, new DatabaseProcessor2<Integer>() {
			@Override
			public Integer process(SQLiteDatabase db) throws SQLiteException {
				return db.delete(table, whereClause, whereArgs);
			}
		});
	}

	public int delete(String table, String whereClause) {
		return delete(table, whereClause, null);
	}

	public int update(final String table, final ContentValues values,
			final String whereClause, String whereArg) {
		return update(table, values, whereClause, new String[] { whereArg });
	}

	public int update(final String table, final ContentValues values,
			final String whereClause, final String[] whereArgs) {
		return execute2(true, new DatabaseProcessor2<Integer>() {
			@Override
			public Integer process(SQLiteDatabase db) throws SQLiteException {
				return db.update(table, values, whereClause, whereArgs);
			}
		});
	}

	public int update(String table, final ContentValues values,
			String whereClause) {
		return update(table, values, whereClause, (String[]) null);
	}

	public void execute(final String sql, final String[] bindArgs) {
		execute(false, new DatabaseProcessor() {
			@Override
			public void process(SQLiteDatabase db) throws SQLiteException {
				db.execSQL(sql, bindArgs);
			}
		});
	}

	public void execute(final String sql) {
		execute(false, new DatabaseProcessor() {
			@Override
			public void process(SQLiteDatabase db) throws SQLiteException {
				db.execSQL(sql);
			}
		});
	}

	public boolean exist(String queryString) throws SQLiteException {
		return exist(queryString, (String[]) null);
	}

	public boolean exist(String queryString, String value)
			throws SQLiteException {
		return exist(queryString, new String[] { value });
	}

	public boolean exist(final String queryString, final String[] values)
			throws SQLiteException {
		return execute2(true, new DatabaseProcessor2<Boolean>() {
			@Override
			public Boolean process(SQLiteDatabase db) throws SQLiteException {
				Cursor cursor = db.rawQuery(queryString, values);
				return exist(cursor);
			}
		});
	}

	public <T> T get(String queryString, RowMapper<T> mapper)
			throws SQLiteException {
		return get(queryString, (String[]) null, mapper);
	}

	public <T> T get(String queryString, String value, RowMapper<T> mapper)
			throws SQLiteException {
		return get(queryString, new String[] { value }, mapper);
	}

	public <T> T get(final String queryString, final String[] values,
			final RowMapper<T> mapper) throws SQLiteException {
		return execute2(true, new DatabaseProcessor2<T>() {
			@Override
			public T process(SQLiteDatabase db) throws SQLiteException {
				Cursor cursor = db.rawQuery(queryString, values);
				return get(cursor, mapper);
			}
		});
	}

	public <T> List<T> query(String queryString, RowMapper<T> mapper)
			throws SQLiteException {
		return query(queryString, (String[]) null, mapper);
	}

	public <T> List<T> query(String queryString, String value,
			RowMapper<T> mapper) throws SQLiteException {
		return query(queryString, new String[] { value }, mapper);
	}

	public <T> List<T> query(final String queryString, final String[] values,
			final RowMapper<T> mapper) throws SQLiteException {
		final List<T> l = new ArrayList<T>();
		execute(true, new DatabaseProcessor() {
			@Override
			public void process(SQLiteDatabase db) throws SQLiteException {
				Cursor cursor = db.rawQuery(queryString, values);
				query(l, cursor, mapper);
			}
		});
		return l;
	}

	public void query(String queryString, RowCallbackHandler handler)
			throws SQLiteException {
		query(queryString, (String[]) null, handler);
	}

	public void query(String queryString, String value,
			RowCallbackHandler handler) throws SQLiteException {
		query(queryString, new String[] { value }, handler);
	}

	public void query(final String queryString, final String[] values,
			final RowCallbackHandler callbackHandler) throws SQLiteException {
		execute(true, new DatabaseProcessor() {
			@Override
			public void process(SQLiteDatabase db) throws SQLiteException {
				Cursor cursor = db.rawQuery(queryString, values);
				query(cursor, callbackHandler);
			}
		});
	}

	public <T> T query(String queryString,
			ScrollableResultsExtractor<T> extractor) throws SQLiteException {
		return query(queryString, (String[]) null, extractor);
	}

	public <T> T query(String queryString, String value,
			ScrollableResultsExtractor<T> extractor) throws SQLiteException {
		return query(queryString, new String[] { value }, extractor);
	}

	public <T> T query(final String queryString, final String[] values,
			final ScrollableResultsExtractor<T> extractor)
			throws SQLiteException {
		return execute2(true, new DatabaseProcessor2<T>() {
			@Override
			public T process(SQLiteDatabase db) throws SQLiteException {
				Cursor cursor = db.rawQuery(queryString, values);
				return query(cursor, extractor);
			}
		});
	}

	public long queryForLong(String queryString) throws SQLiteException {
		return queryForLong(queryString, (String[]) null);
	}

	public long queryForLong(String queryString, String value)
			throws SQLiteException {
		return queryForLong(queryString, new String[] { value });
	}

	public long queryForLong(String queryString, String[] values)
			throws SQLiteException {
		SQLiteDatabase db = createSQLiteDatabase(true);
		Cursor cursor = db.rawQuery(queryString, values);
		return queryForLong(cursor);
	}

	public short queryForShort(String queryString) throws SQLiteException {
		return queryForShort(queryString, (String[]) null);
	}

	public short queryForShort(String queryString, String value)
			throws SQLiteException {
		return queryForShort(queryString, new String[] { value });
	}

	public short queryForShort(String queryString, String[] values)
			throws SQLiteException {
		SQLiteDatabase db = createSQLiteDatabase(true);
		Cursor cursor = db.rawQuery(queryString, values);
		return queryForShort(cursor);
	}

	public int queryForInt(String queryString) throws SQLiteException {
		return queryForInt(queryString, (String[]) null);
	}

	public int queryForInt(String queryString, String value)
			throws SQLiteException {
		return queryForInt(queryString, new String[] { value });
	}

	public int queryForInt(String queryString, String[] values)
			throws SQLiteException {
		SQLiteDatabase db = createSQLiteDatabase(true);
		Cursor cursor = db.rawQuery(queryString, values);
		return queryForInt(cursor);
	}

	public float queryForFloat(String queryString) throws SQLiteException {
		return queryForFloat(queryString, (String[]) null);
	}

	public float queryForFloat(String queryString, String value)
			throws SQLiteException {
		return queryForFloat(queryString, new String[] { value });
	}

	public float queryForFloat(String queryString, String[] values)
			throws SQLiteException {
		SQLiteDatabase db = createSQLiteDatabase(true);
		Cursor cursor = db.rawQuery(queryString, values);
		return queryForFloat(cursor);
	}

	public String queryForString(String queryString) throws SQLiteException {
		return queryForString(queryString, (String[]) null);
	}

	public String queryForString(String queryString, String value)
			throws SQLiteException {
		return queryForString(queryString, new String[] { value });
	}

	public String queryForString(String queryString, String[] values)
			throws SQLiteException {
		SQLiteDatabase db = createSQLiteDatabase(true);
		Cursor cursor = db.rawQuery(queryString, values);
		return queryForString(cursor);
	}

	public final void execute(boolean readOnly, DatabaseProcessor processor) {
		final Lock lock = readOnly ? rLock : wLock;
		lock.lock();

		SQLiteDatabase db = createSQLiteDatabase(readOnly);
		try {
			processor.process(db);
		} finally {
			db.close();
			lock.unlock();
		}
	}

	public final <T> T execute2(boolean readOnly,
			DatabaseProcessor2<T> processor) {
		final Lock lock = readOnly ? rLock : wLock;
		lock.lock();

		SQLiteDatabase db = createSQLiteDatabase(readOnly);
		try {
			return processor.process(db);
		} finally {
			db.close();
			lock.unlock();
		}
	}

	protected abstract SQLiteDatabase createSQLiteDatabase(boolean readOnly);

	public interface DatabaseProcessor {
		public void process(SQLiteDatabase db) throws SQLiteException;
	}

	public interface DatabaseProcessor2<T> {
		public T process(SQLiteDatabase db) throws SQLiteException;
	}

	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final Lock wLock = lock.writeLock();
	private final Lock rLock = lock.readLock();
}
