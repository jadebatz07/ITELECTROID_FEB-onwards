package com.example.crudlistpic_database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class PersonDatabase extends SQLiteOpenHelper {

	static String DB="db_person";
	static String PERSON="tbl_person";
	
	public PersonDatabase(Context context) {
		super(context, DB, null, 1);
		// create a database at the SQLite database MS
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="CREATE TABLE "+PERSON+"(id integer primary key autoincrement, imageuri varchar(50), name varchar(50))";
		db.execSQL(sql);
	}
	
	//add a new person method
	public long addPerson(String imageuri, String name){
		long result=0;
			ContentValues cv= new ContentValues();
				cv.put("imageuri", imageuri);
				cv.put("name", name);
			//open a database connection, for writing data
				SQLiteDatabase db=this.getWritableDatabase();
				result=db.insert(PERSON, null, cv);
				db.close();
		return result;
		
	}
	//update the person
	public int updatePerson(String imageuri, String name, int id){
		int result=0;
			ContentValues cv= new ContentValues();
				cv.put("imageuri", imageuri);
				cv.put("name", name);
			//open a database connection, for writing data
				SQLiteDatabase db=this.getWritableDatabase();
				result=db.update(PERSON, cv, "id="+id, null);
				db.close();
		return result;
		
	}
	
	//delete a record
	public int deletePerson(int id){
		int result =0;
		//open a writable database connection
		SQLiteDatabase db=this.getWritableDatabase();
			result=db.delete(PERSON,"id="+id,null);
		return result;
	}
	 
	
	//retrieve all records from the table PERSON
	public ArrayList<Person> getAllPerson(){
		ArrayList<Person> list=new ArrayList<Person>();
		//create a database connection, for reading data
		SQLiteDatabase db=this.getReadableDatabase();
			Cursor c= db.query(PERSON, null, null, null, null, null,"name");
			c.moveToFirst();//reposition the record pointer to the first record
			while(!c.isAfterLast()){
				
				String imageuri=c.getString(c.getColumnIndex("imageuri"));
				String name=c.getString(c.getColumnIndex("name"));
				int id  = c.getInt(c.getColumnIndex("id"));
				Uri image=Uri.parse(imageuri);
				Person p= new Person(image,name, id);
				list.add(p); 				
				c.moveToNext(); //traverse the record pointer to the next record
			}
			db.close(); //close database
		return list;
	}
	
	//count the number of records and return
	public int getCount(){
		return this.getAllPerson().size();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
