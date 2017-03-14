package com.example.mytabhost;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
	TabHost th;
	String lng="", lat="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        th = this.getTabHost();
       
        
        Intent addloc = new Intent(this, AddLocation.class);
        Intent loclist = new Intent(this, LocationList.class);
        
        Intent aboutme = new Intent(this, AboutMe.class);
        
        Bundle b = this.getIntent().getExtras();
		if(b != null)
		{
			 lng = b.getString("lng");
			 lat = b.getString("lat");
			 loclist.putExtra("lng", lng);
		     loclist.putExtra("lat", lat);
		}
        
        Drawable add = this.getResources().getDrawable(R.drawable.add);
        Drawable list = this.getResources().getDrawable(R.drawable.mylocation);
        Drawable me = this.getResources().getDrawable(R.drawable.share);
        
        TabSpec locationlist = th.newTabSpec("list")
        		.setIndicator("", list)
        		.setContent(loclist);
        
        TabSpec addlocation = th.newTabSpec("addloc")
        		.setIndicator("", add)
        		.setContent(addloc);
        
        TabSpec boutme = th.newTabSpec("me")
        		.setIndicator("", me)
        		.setContent(aboutme);
        
       th.addTab(locationlist);
       th.addTab(addlocation);
       th.addTab(boutme);
        
    }

    
}
