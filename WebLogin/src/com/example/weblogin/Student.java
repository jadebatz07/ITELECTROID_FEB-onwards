package com.example.weblogin;

public class Student {

	String idno,familyname,givename,course,yearlevel,campus;

	public Student(String idno, String familyname, String givename,
			String course, String yearlevel, String campus) {
		super();
		this.idno = idno;
		this.familyname = familyname;
		this.givename = givename;
		this.course = course;
		this.yearlevel = yearlevel;
		this.campus = campus;
	}

	public Student(){}
	
	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getGivename() {
		return givename;
	}

	public void setGivename(String givename) {
		this.givename = givename;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getYearlevel() {
		return yearlevel;
	}

	public void setYearlevel(String yearlevel) {
		this.yearlevel = yearlevel;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	} 
	
	
	
}
