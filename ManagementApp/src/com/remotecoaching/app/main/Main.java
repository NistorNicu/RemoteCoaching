package com.remotecoaching.app.main;

import java.util.ArrayList;
import java.util.List;

import com.remotecoaching.app.models.Group;
import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.models.User;
import com.remotecoaching.app.persistence.GroupDataAccessObject;
import com.remotecoaching.app.persistence.MyDataSource;
import com.remotecoaching.app.persistence.RoleDataAccessObject;
import com.remotecoaching.app.persistence.UserDataAccessObject;

public class Main {

	public static void main(String[] args) {
		UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
		MyFile file = new MyFile();
		List<User> usersList = userDataAccessObject.getAll();
		for (User user : usersList){
			System.out.println(user);
			Session.startSession(user);
			file.exists();
			file.read();
			file.write();
		}
	}
	

}
