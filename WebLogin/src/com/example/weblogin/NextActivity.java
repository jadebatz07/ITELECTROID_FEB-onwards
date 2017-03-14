package com.example.weblogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NextActivity extends Activity {

	ListView lv;
	ItemAdapter adapter;
	ArrayList<Student> list =new ArrayList<Student>();
	//ArrayAdapter<String> adapt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.nextview);
		this.lv=(ListView) this.findViewById(R.id.listView1);
        adapter=new ItemAdapter(this,list);
        this.lv.setAdapter(adapter);
        
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        try {
			URL url =new URL("http://10.0.2.2/android/androidweb/listforandroid.php");
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			
			int c=0;
			InputStream is=conn.getInputStream();
			StringBuffer sb=new StringBuffer();
			while((c=is.read())!=-1){//-1 end of file
				sb.append((char)c);
			}	
			is.close();
			conn.disconnect();
			//Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
			String jsonstring=sb.toString();
			
			JSONObject json = new JSONObject(jsonstring);
			JSONArray studentArray =json.getJSONArray("student");
			
			for(int i=0; i < studentArray.length(); i++){
				JSONObject student=(JSONObject) studentArray.get(i);
				Student s=new Student();
				s.setIdno(student.getString("idno"));
				s.setFamilyname(student.getString("familyname"));
				s.setGivename(student.getString("givenname"));
				s.setCourse(student.getString("course"));
				s.setYearlevel(student.getString("yearlevel"));
				s.setCampus(student.getString("campus"));
				list.add(s);
			}
			adapter.notifyDataSetChanged();
			
			//Toast.makeText(this, studentArray.length()+"sdfsdf", Toast.LENGTH_LONG).show();
        } catch (MalformedURLException e) {
        	//Toast.makeText(this, "m", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (IOException e) {
			//Toast.makeText(this, "i", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (JSONException e) {
			//Toast.makeText(this,"j", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
        //adapter.notifyDataSetChanged();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent=new Intent(this,studentform.class);
		this.startActivityForResult(intent, 0); //add new item
		return super.onOptionsItemSelected(item);
	}
	
}
