package com.example.mytabhost;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocationList extends Activity {
	
	ListView lv;
	ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	Database db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.location_list);
		
		db = new Database(this);
		
		if(db.getAllLocation() != null)
		{
			list = db.getAllLocation();
		}
		
		lv = (ListView) this.findViewById(R.id.listView1);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		lv.setAdapter(adapter);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		Bundle b = data.getExtras();
		if(b != null)
		{
			String lng = b.getString("lng");
			String lat = b.getString("lat");
			list.add("Longitude:   "+lng+"\nLatitude:   "+lat);
			adapter.notifyDataSetChanged();
		}
	}

}
