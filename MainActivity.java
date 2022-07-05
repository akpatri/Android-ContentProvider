package com.provider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	EditText er,en,em,txt;
	String col_roll,col_name,col_marks;
	dbHelper dh;
	ContentResolver cr;
	Uri[] uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		er=findViewById(R.id.roll);
		en=findViewById(R.id.name);
		em=findViewById(R.id.marks);
		txt=findViewById(R.id.txt);
		uri=new Uri[]{Contract.tab_Stud.uri.url_table,Contract.tab_Stud.uri.url_row};
		col_name=Contract.tab_Stud.col.name;
		col_marks=Contract.tab_Stud.col.marks;
		col_roll=Contract.tab_Stud.col._ID;
		dh=new dbHelper(this);
		cr=getContentResolver();
		
    }
	public void insert(View v){
		String[] data={er.getText().toString(),en.getText().toString(),em.getText().toString()};
		ContentValues cv=new ContentValues();
		cv.put(col_roll,data[0]);
		cv.put(col_name,data[1]);
		cv.put(col_marks,data[2]);
		Uri u=cr.insert(uri[0],cv,null);
		txt.setText(String.format("Roll:%s inserted",u.getLastPathSegment()));
			
	}
	public void delete(View v){
		String where=col_roll+"=?";
		String[] arg={er.getText().toString()};
		int del=cr.delete(uri[0],where,arg);
		txt.setText(String.format("%s row effected",del));
	}
	public void update(View v){
		String where=col_roll+"=?";
		String[] arg={er.getText().toString()};
		String[] data={er.getText().toString(),en.getText().toString(),em.getText().toString()};
		ContentValues cv=new ContentValues();
		cv.put(col_roll,data[0]);
		cv.put(col_name,data[1]);
		cv.put(col_marks,data[2]);
		int up=cr.update(uri[0],cv,where,arg);
		txt.setText(String.format("%s row effected",up));		
	}
	public void retrive(View v){
		Cursor c=cr.query(uri[0],null,null,null,null);
		StringBuffer sb=new StringBuffer();
		while(c.moveToNext()){
			sb.append("Roll :"+c.getString(0)+"\n");
			sb.append("Name :"+c.getString(1)+"\n");
			sb.append("Marks :"+c.getString(2)+"\n\n");
		}
		AlertDialog.Builder ab=new AlertDialog.Builder(this);
		ab.setCancelable(true);
		ab.setMessage(sb.toString());
		ab.setTitle("UserData");
		ab.show();
	}
	public void deleteD(View v){
		dh.del();
	}
	
}