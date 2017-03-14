package com.example.weblogin;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

	Context context;
	ArrayList<Student> list;
	LayoutInflater inflater;
	
	
	
	public ItemAdapter(Context context, ArrayList<Student> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater=LayoutInflater.from(context);
	} 

	@Override
	public int getCount() { 
		// TODO Auto-generated method stub
		return list.size();
	}
 
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		ItemHandler handler=null;
		if(arg1==null){
			arg1=this.inflater.inflate(R.layout.itemlayout,null);
			handler=new ItemHandler();
			handler.name=(TextView)arg1.findViewById(R.id.textView1);
			handler.idno=(TextView)arg1.findViewById(R.id.textView2);
			handler.course=(TextView)arg1.findViewById(R.id.textView3);
			//handler.yearlevel=(TextView) arg1.findViewById(R.id.spinner2);
			handler.campus=(TextView)arg1.findViewById(R.id.textView4);
			arg1.setTag(handler);
		}
		else handler=(ItemHandler) arg1.getTag();
		
		handler.name.setText(list.get(arg0).getFamilyname()+","+list.get(arg0).getGivename());
		handler.idno.setText(list.get(arg0).getIdno());
		handler.course.setText(list.get(arg0).getCourse()+"-"+list.get(arg0).getYearlevel());
		handler.campus.setText(list.get(arg0).getCampus());
		
		return arg1;
	}
	
	static class ItemHandler{
		TextView name,idno,course,yearlevel,campus;
	}

}
