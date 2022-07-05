package com.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.content.ContentValues;
import android.net.Uri;

import android.content.ContentProvider;
import android.widget.Toast;

public class contentP extends ContentProvider {
	Context c;
	dbHelper dh;
	static UriMatcher um;
	static Uri[] uri;

	@Override
	public boolean onCreate() {
		c = getContext();
		dh = new dbHelper(c);
		return true;

	}

	static {
		uri = new Uri[] { Contract.tab_Stud.uri.url_table, Contract.tab_Stud.uri.url_row };
		um = new UriMatcher(UriMatcher.NO_MATCH);
		um.addURI(uri[0].getAuthority(), uri[0].getPath(), 1);
		um.addURI(uri[1].getAuthority(), uri[1].getPath(), 2);

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

		switch (um.match(uri)) {
		case 1: {
			return dh.query(projection, selection, selectionArgs, null);
		}
		
		case 2: {
			Toast.makeText(c,"delete test",Toast.LENGTH_SHORT).show();
		}
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		switch (um.match(uri)) {
		case 1: {
			return "ven.cursor.dir/ven.auth.student";
		}
		case 2: {
			return "ven.cursor.item/ven.auth.student" +uri.getLastPathSegment();
		}
		}
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		switch (um.match(uri)) {
		case 1: {
			return ContentUris.withAppendedId(uri,dh.insert(values)) ;
		}
		case 2: {
			Toast.makeText(c,"Inser test",Toast.LENGTH_SHORT).show();
		}
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		switch (um.match(uri)) {
		case 1: {
			return dh.delete(selection,selectionArgs);
		}
		case 2: {
			Toast.makeText(c,"delete test",Toast.LENGTH_SHORT).show();
		}
		}

		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		switch (um.match(uri)) {
		case 1: {
			return dh.update(values,selection,selectionArgs);	
		}
		case 2: {
			Toast.makeText(c,"test",Toast.LENGTH_SHORT).show();
		}
		}
		return 0;
	}

}
