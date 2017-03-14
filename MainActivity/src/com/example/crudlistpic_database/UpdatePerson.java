package com.example.crudlistpic_database;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.CrudPicListNew.R;

public class UpdatePerson extends Activity implements OnClickListener {
	
	ImageView iv; 
	EditText txtName;
	Button btnSave, btnCancel;
	
    int itemid;
    Uri uri;
	PersonDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.updateitem);
		db = new PersonDatabase(this);
		this.iv=(ImageView) this.findViewById(R.id.imageView1);
		this.txtName=(EditText) this.findViewById(R.id.editText1);
		
		this.btnSave=(Button) this.findViewById(R.id.button1);
		this.btnCancel=(Button) this.findViewById(R.id.button2);
		
		this.iv.setOnClickListener(this);
		this.btnSave.setOnClickListener(this);
		this.btnCancel.setOnClickListener(this);
		
		Intent intent=this.getIntent();
		Bundle b=intent.getExtras();
		if(b!=null){
			String name=b.getString("selectedName");
			Uri r =b.getParcelable("selectedUri");
			itemid = b.getInt("selectedId");
			this.txtName.setText(name);
			this.iv.setImageURI(r);
			Toast.makeText(this, itemid + "",  Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onClick(View arg0) {
		int id=arg0.getId();
		switch(id){
		case R.id.imageView1:// create an image picker
			Intent intent=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			this.startActivityForResult(intent, 111);
			break;
			
		case R.id.button1: //save
			String name = txtName.getText().toString();
			if(itemid != 0){
				db.updatePerson(uri.toString(), name,itemid);
				startActivity(new Intent (this,MainActivity.class));
			}else if(uri!=null && !name.equals("")  ){
				Intent intent2 = new Intent(this, MainActivity.class);
				intent2.putExtra("uri", uri);
				intent2.putExtra("name", name);
				this.setResult(Activity.RESULT_OK, intent2);
			}
			else Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
			
			//break;
		case R.id.button2: //cancel
			this.finish(); //terminate activity
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		uri=data.getData();
		
		this.iv.setImageURI(uri);
	}
  
	
	
}
