package com.example.weblogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class studentform extends Activity implements OnClickListener, OnItemSelectedListener {
	
	EditText txtId,txtfname,txtgname,txtcampus;
	Spinner cboCourse,cboYear;
	Button btnSend,btnCancel;
	private String selectedCourse;
	private String selectedYear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentform);
		
		this.txtId=(EditText) this.findViewById(R.id.editText1);
		this.txtfname=(EditText) this.findViewById(R.id.editText2);
		this.txtgname=(EditText) this.findViewById(R.id.editText3);
		this.txtcampus=(EditText) this.findViewById(R.id.editText4);
		this.cboCourse=(Spinner) this.findViewById(R.id.spinner1);
        this.cboYear=(Spinner) this.findViewById(R.id.spinner2);
        this.btnSend=(Button) this.findViewById(R.id.button1);
        this.btnCancel=(Button) this.findViewById(R.id.button2);
        
        this.cboCourse.setOnItemSelectedListener(this);
        this.cboYear.setOnItemSelectedListener(this);
        
        this.btnSend.setOnClickListener(this);
        this.btnCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
	
		int id=arg0.getId();
		switch(id){
		case R.id.button1: //save
			String tid=this.txtId.getText().toString();
			String fname=this.txtfname.getText().toString();
			String gname=this.txtgname.getText().toString();
			String camp=this.txtcampus.getText().toString();
			
			String[] mname=gname.split("\\ "); //check for space between name word
			StringBuffer cleanName=new StringBuffer();
			for(String s:mname){
				cleanName.append(s).append("%20");
			}
			//DIRI NAKO !!!!!!!!!
			try {
				URL url=new URL("http://10.0.2.2/android/androidweb/addstudent.php?idno="+tid+"&lname="+fname+"&fname="+cleanName.toString()+"&course="+selectedCourse+"&year="+selectedYear+"&campus="+camp);
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				//receive the server response
				InputStream is=conn.getInputStream();
				int c=0;
				StringBuffer sb=new StringBuffer();
				while((c=is.read())!=-1){					
					sb.append((char)c);					
				}
				//after receive, close all streams
				is.close();
				//disconnect the server
				conn.disconnect();
				
				
				Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
				startActivity(new Intent(this,NextActivity.class));
			
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//break;
		case R.id.button2: //cancel
			this.finish();
		
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		selectedCourse=this.cboCourse.getItemAtPosition(arg2).toString();
		selectedYear=this.cboYear.getItemAtPosition(arg2).toString();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}