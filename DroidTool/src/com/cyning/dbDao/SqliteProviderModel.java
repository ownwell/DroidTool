package com.cyning.dbDao;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public abstract class SqliteProviderModel {

	private UriMatcher uriMatcher;
	private SQLiteDatabase database;

	public SqliteProviderModel() {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	}

	public int match(Uri uri) {
		return getUriMatcher().match(uri);
	}

	void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}

	protected UriMatcher getUriMatcher() {
		return uriMatcher;
	}

	protected SQLiteDatabase getDatabase() {
		return database;
	}

	public abstract void createUriMap();

	public abstract Cursor onQuery(int code, Uri uri, String[] projection,
			String selection, String[] selectionArgs, String sortOrder);

	public abstract Uri onInsert(int code, Uri uri, ContentValues values);

	public abstract int onDelete(int code, Uri uri, String selection,
			String[] selectionArgs);

	public abstract int onUpdate(int code, Uri uri, ContentValues values,
			String where, String[] whereArgs);

	public String onGetType(int code, Uri uri) {
		return null;
	}

}
