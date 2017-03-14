package com.example.crudlistpic_database;

import android.net.Uri;

public class Person {
	
	private Uri imageUri;
	private String name;
	private int id;
	public Person(Uri imageUri, String name, int id) {
		super();
		this.imageUri = imageUri;
		this.name = name;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Uri getImageUri() {
		return imageUri;
	}

	public void setImageUri(Uri imageUri) {
		this.imageUri = imageUri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	

}
