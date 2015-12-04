package com.remotecoaching.app.main;

import java.util.List;

import com.remotecoaching.app.exceptions.SessionUninitializedException;
import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.models.User;

public class Session {
	public static final String PERMISSION_EXIST = "exist";
	public static final String PERMISSION_READ = "read";
	public static final String PERMISSION_WRITE = "write";
	private static Session instance = new Session();
	private static User sessionUser;

	private Session() {

	}

	public static void startSession(User user) {
		instance.sessionUser = user;
	}

	public static Session getInstance() throws SessionUninitializedException {
		if (sessionUser == null) {
			throw new SessionUninitializedException();
		}
		return instance;
	}

	public List<Role> getSessionRoles() {
		return sessionUser.getGroup().getRoles();
	}

	public boolean checkPermission(String permission) {
		for (Role r : instance.sessionUser.getGroup().getRoles()) {
			if (r.getName().equals(permission)) {
				return true;
			}
		}
		return false;
	}
}
