package com.example.crudlistpic_database;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.CrudPicListNew.R;

public class MainActivity extends Activity implements OnItemClickListener {
	
	//EditText txtSearch;
	ListView lv;
	ArrayList<Person> list=new ArrayList<Person>();
	ArrayList<Person> source= new ArrayList<Person>();
	ItemAdapter adapter;
	PersonDatabase db;
	AdapterView.AdapterContextMenuInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db=new PersonDatabase(this);
        if(db.getCount()>0){
        	list=db.getAllPerson();
        }
        this.lv=(ListView) this.findViewById(R.id.listView1);
        this.adapter=new ItemAdapter(this, list);
        this.lv.setAdapter(adapter); 
        
        this.registerForContextMenu(lv);
        
        lv.setOnItemClickListener(this);  
    }

    

    @Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		info=(AdapterContextMenuInfo) menuInfo;
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		String name=list.get(info.position).getName();
		menu.setHeaderTitle(name+" id "+list.get(info.position).getId());
	}



	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id=item.getItemId();
		
		
		switch(id){
		case R.id.update:
			String itemSelected=list.get(info.position).getName();
			int itemSelected3=list.get(info.position).getId();
			Uri itemSelected2=list.get(info.position).getImageUri();
			Intent intent=new Intent(this, UpdatePerson.class);	
            intent.putExtra("selectedName", itemSelected)	;	
            intent.putExtra("selectedUri", itemSelected2)	;	
            intent.putExtra("selectedId", itemSelected3)	;	
            this.startActivityForResult(intent,1); 
			
			break;
		case R.id.delete:
			//list.remove(info.position);
			//db.deletePerson(list.get(info.position).getId());
			db.deletePerson(list.remove(info.position).getId());
			
			adapter.notifyDataSetChanged();
		}
		adapter.notifyDataSetChanged();
		return super.onContextItemSelected(item);
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent=new Intent(this,UpdatePerson.class);
		this.startActivityForResult(intent, 0); //add new item
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==0 && resultCode==Activity.RESULT_OK){
			Bundle b=data.getExtras();
			Uri uri=b.getParcelable("uri");
			String name=b.getString("name");
			switch(requestCode){
			case 0:

				//update the database
				db.addPerson(uri.toString(),name);
				list = db.getAllPerson();
				startActivity(new Intent(MainActivity.this,MainActivity.class));
				break;
			case 1:
				//list.set((new Person(info.position, name));			
			}		
			this.adapter.notifyDataSetChanged();
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(list.get(arg2).getName());
		
		ImageView myiv=new ImageView(this);
			myiv.setImageURI(list.get(arg2).getImageUri());
			TextView myname=new TextView(this);
			myname.setText(list.get(arg2).getName());
		
		
		LinearLayout mainLayout=new LinearLayout(this);	
				mainLayout.setOrientation(LinearLayout.HORIZONTAL);
				mainLayout.addView(myiv);
				
		LinearLayout subLayout=new LinearLayout(this);
			subLayout.setOrientation(LinearLayout.VERTICAL);
			subLayout.addView(myname);
			
			mainLayout.addView(subLayout);
			
			builder.setView(mainLayout);
			builder.setNeutralButton("Okey", null);
			
	AlertDialog dialog=builder.create();
		dialog.show();

	}
    
    
}
