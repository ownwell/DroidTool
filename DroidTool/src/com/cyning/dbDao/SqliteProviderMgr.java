package com.cyning.dbDao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

public abstract class SqliteProviderMgr extends ContentProvider {

	private SQLiteDatabase database;

	protected List<SqliteProviderModel> models;

	@Override
	public boolean onCreate() {

		database = createDatabase();

		models = new ArrayList<SqliteProviderModel>();
		addProviderModel(models);

		for (SqliteProviderModel model : models) {
			model.setDatabase(database);

			// create all uri map
			model.createUriMap();
		}

		return true;
	}

	protected abstract SQLiteDatabase createDatabase() throws SQLiteException;

	protected abstract void addProviderModel(List<SqliteProviderModel> models);

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		for (SqliteProviderModel model : models) {
			int code = model.match(uri);
			if (code != UriMatcher.NO_MATCH) {
				return model.onQuery(code, uri, projection, selection,
						selectionArgs, sortOrder);
			}
		}

		throw new IllegalArgumentException("Uri IllegalArgument:" + uri);
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		for (SqliteProviderModel model : models) {
			int code = model.match(uri);
			if (code != UriMatcher.NO_MATCH) {
				return model.onInsert(code, uri, values);
			}
		}

		throw new IllegalArgumentException("Uri IllegalArgument:" + uri);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		for (SqliteProviderModel model : models) {
			int code = model.match(uri);
			if (code != UriMatcher.NO_MATCH) {
				return model.onDelete(code, uri, selection, selectionArgs);
			}
		}

		throw new IllegalArgumentException("Uri IllegalArgument:" + uri);
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {

		for (SqliteProviderModel model : models) {
			int code = model.match(uri);
			if (code != UriMatcher.NO_MATCH) {
				return model.onUpdate(code, uri, values, where, whereArgs);
			}
		}

		throw new IllegalArgumentException("Uri IllegalArgument:" + uri);
	}
}
