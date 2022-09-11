package com.revature.webstore.models;

import java.util.Date;
import java.sql.Timestamp;

public class User {
	private int userId;
	private String fName;
	private String lName;
	private String email;
	private String pass;
	private Timestamp createdAt;
	
	public User() {
	}
	
	public User(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}

	public User(int userId, String fName, String lName) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
	}

	public User(String fName, String lName, String email, String pass) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pass = pass;
	}

	public User(int userId, String fName, String lName, String email, String pass, Timestamp createdAt) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.pass = pass;
		this.createdAt = createdAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
    public String toString() {
		return "{" + this.userId + this.fName + this.lName + this.email + this.pass + this.createdAt + "}";
	}

}
