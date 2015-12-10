package com.remotecoaching.app.main;

import java.util.List;

import com.remotecoaching.app.models.User;
import com.remotecoaching.app.service.FileService;
import com.remotecoaching.app.service.UserService;

public class Main {

	public static void main(String[] args) {
		UserService userDataAccessObject = new UserService();
		MyFile file = new FileService();
		List<User> usersList = userDataAccessObject.getAll();
		for (User user : usersList) {
			System.out.println(user);
			Session.startSession(user);
			file.exists();
			file.read();
			file.write();
		}
	}

}
