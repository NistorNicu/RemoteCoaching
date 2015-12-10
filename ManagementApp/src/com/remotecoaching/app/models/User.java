package com.remotecoaching.app.models;

import java.io.NotActiveException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.remotecoaching.app.exceptions.NotValidEmailException;
import com.remotecoaching.app.service.UserService;

public class User {

	private int id;
	private String userName;
	private String email;
	private Group group = new Group();
	
	public User() {
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

	public void setEmail(String email) throws NotValidEmailException {
		if (UserService.validate(email)) {
			this.email = email;
		} else {
			throw new NotValidEmailException();
		}
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", group=" + group + "]";
	}

}
