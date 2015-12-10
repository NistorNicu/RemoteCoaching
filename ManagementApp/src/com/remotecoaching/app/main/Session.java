package com.remotecoaching.app.main;

import java.util.List;
import java.util.Set;

import com.remotecoaching.app.exceptions.SessionUninitializedException;
import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.models.User;

public class Session {
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

	public Set<Role> getSessionRoles() {
		return sessionUser.getGroup().getRoles();
	}

	
}
