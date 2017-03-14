package com.example.mytabhost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AddLocation extends Activity implements OnClickListener, LocationListener {
	
	TextView lng, lat;
	Button save, cancel;
	Database db;
	LocationManager manager;
	android.location.Location location;
	Criteria criteria;
	String provider;
	double longi, lati;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.addlocation);
		
		lng = (TextView) this.findViewById(R.id.textView2);
		lat = (TextView) this.findViewById(R.id.textView4);
		save = (Button) this.findViewById(R.id.button1);
		cancel = (Button) this.findViewById(R.id.button2);
		db = new Database(this);
		
		manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
		provider = manager.getBestProvider(criteria, true);
		manager.requestLocationUpdates(provider, 1, 10, this);
		location = manager.getLastKnownLocation(provider);
		
		longi = location.getLongitude();
		lati = location.getLatitude();
		
		
		this.save.setOnClickListener(this);
		this.cancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		
		switch(arg0.getId())
		{
		case R.id.button1:
			
			String lng = ""+longi;
			String lat = ""+lati;
			
			this.lng.setText(lng);
			this.lat.setText(lat);
			
			db.addLocation(lng, lat);
			
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("lng", lng);
			intent.putExtra("lat", lat);
			this.startActivity(intent);
			
			break;
			
		case R.id.button2:	this.finish();
			
			
		}
		
	}

	@Override
	public void onLocationChanged(android.location.Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

}
