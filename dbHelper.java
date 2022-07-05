package com.provider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.provider.Contract;

public class dbHelper extends SQLiteOpenHelper {
	EditText tv;
	String table_name = Contract.tab_Stud.name;
	String name = Contract.tab_Stud.col.name;
	String roll = Contract.tab_Stud.col._ID;
	String mark = Contract.tab_Stud.col.marks;
	String createTable = String.format("create table if not exists %s (%s integer,%s varchar,%s integer);", table_name,
			roll, name, mark);
	String drop_table = String.format("drop table %s", table_name);
	Context a;
	SQLiteDatabase db;

	public dbHelper(Context a) {
		super(a, "database", null, 1);
		this.a = a;
		db = getWritableDatabase();
	//	Toast.makeText(a, "Database created Sucessifull", Toast.LENGTH_SHORT).show();
		this.db = db;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(createTable);
			Toast.makeText(a, "table Created", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(a, "Exception in CreationTable", Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		try {
			Toast.makeText(a, "Sucessfull Upgrade", Toast.LENGTH_SHORT).show();
			db.execSQL(drop_table);
			onCreate(db);
		} catch (Exception e) {
			Toast.makeText(a, "Exception in upgrade", Toast.LENGTH_SHORT).show();
		}
	}

	public long insert(ContentValues cv) {
		try {
			long ins = db.insert(table_name, null, cv);
			Toast.makeText(a, "Sucessifully Insert", Toast.LENGTH_SHORT).show();
			return ins;
		} catch (Exception e) {
			Toast.makeText(a, "Exception in Insert", Toast.LENGTH_SHORT).show();
			return -1;
		}

	}

	public int update(ContentValues cv, String where, String[] arg) {
		try {
			int upd = db.update(table_name, cv, where, arg);
			Toast.makeText(a, "Sucessifully Update", Toast.LENGTH_SHORT).show();
			return upd;

		} catch (Exception e) {
			Toast.makeText(a, "Exception in update", Toast.LENGTH_SHORT).show();
			return -1;
		}

	}

	public int delete(String where, String[] arg) {

		try {
			int del = db.delete(table_name, where, arg);
			Toast.makeText(a, "sucessifully delete", Toast.LENGTH_SHORT).show();
			return del;
		} catch (Exception e) {
			Toast.makeText(a, "Exception in delete", Toast.LENGTH_SHORT).show();
			return -1;
		}
	}

	public Cursor query(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		try {
			Cursor cr = db.query(table_name, projection, selection, selectionArgs, null, null, sortOrder);
			Toast.makeText(a, "sucessifully retrive", Toast.LENGTH_SHORT).show();
			;
			return cr;
		} catch (Exception e) {
			Toast.makeText(a, "Exception in retrive", Toast.LENGTH_SHORT).show();
			return null;
		}
	}
	
		public void del() {
			try {
				db.close();
				close();
				a.deleteDatabase("database");
				Toast.makeText(a, "Database Deleted", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				Toast.makeText(a, "ExceptionDataabse delete", Toast.LENGTH_SHORT).show();
			}
		}
}
