package com.remotecoaching.app.models;

public class User {
	
	private int id;
	private String userName;
	private String email;
	private Group group;
	
	public User(){
	}

	public User(int id, String userName, String email, Group group) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.group = group;
	}
	
	

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
