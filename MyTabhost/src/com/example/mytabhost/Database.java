package com.example.mytabhost;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	
	Context context;
	final static String DATABASE="loc_db";
	final static String TABLE="loc_table";
	
	public Database(Context context) {
		super(context, DATABASE, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
		String sql="CREATE TABLE "+TABLE+"(id integer primary key autoincrement, longitude varchar(20), latitude varchar (20))";
		arg0.execSQL(sql);

	}
	
	public long addLocation(String lng, String lat)
	{
		long res = 0;
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("longitude", lng);
		cv.put("latitude", lat);
		
		res = db.insert(TABLE, null, cv);
		
		db.close();
		
		
		return res;
	}
	
	public ArrayList<String> getAllLocation()
	{
		ArrayList<String> list = new ArrayList<String>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.query(TABLE, null, null, null, null, null, "id");
		
		c.moveToFirst();
		
		while(!c.isAfterLast())
		{
			String lng = c.getString(c.getColumnIndex("longitude"));
			String lat = c.getString(c.getColumnIndex("latitude"));
			
			list.add("Longitude: "+lng+"\nLatitude: "+lat);
			
			c.moveToNext();
			
		}
		
		c.close();
		db.close();
		
		
		return list;
	}
	
	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
