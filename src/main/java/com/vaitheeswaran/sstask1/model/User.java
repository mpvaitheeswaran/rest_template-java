package com.vaitheeswaran.sstask1.model;

public class User {
	String firstname;
	String lastname;
	String mobile;
	public User() {}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		
		return "Firstname : "+getFirstname()+", "+"Lastname : "+getLastname()+", "+"Mobile : "+getMobile();
	}
}
