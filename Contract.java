package com.provider;

import android.net.Uri;
import android.provider.BaseColumns;
import java.net.URI;
//uri,table,columns
public class Contract{
	public static final class tab_Stud{
		public static final String name="student";
		public static final class col implements BaseColumns{
			//write all column info for table here
			public static final String name="name";
			public static final String marks="marks";
		}
		public static final class uri{
			//write all Uri here
			//for whole table
			public static final Uri url_table=Uri.parse("content://auth/student");
			//for single row
			public static final Uri url_row=Uri.parse("content://auth/student/3");
			
		} 	
	}
	
}
