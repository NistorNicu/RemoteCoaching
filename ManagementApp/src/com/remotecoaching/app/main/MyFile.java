package com.remotecoaching.app.main;

import com.remotecoaching.app.exceptions.SessionUninitializedException;
import com.remotecoaching.app.models.Role;

public class MyFile implements File {

	@Override
	public void exists() {
		try {
			if (Session.getInstance().checkPermission(Session.PERMISSION_WRITE)) {
				System.out.println("Checking if exists");
			} else {
				System.out.println("Current user have no permission for this operation");
			}
		} catch (SessionUninitializedException e) {
			System.out.println(
					"For file operations session must be initialized with a user via Session.startSession....");
			e.printStackTrace();
		}

	}

	@Override
	public void write() {
		try {
			if (Session.getInstance().checkPermission(Session.PERMISSION_WRITE)) {
				System.out.println("Writing...");
			} else {
				System.out.println("Current user have no permission for this operation");
			}
		} catch (SessionUninitializedException e) {
			System.out.println(
					"For file operations session must be initialized with a user via Session.startSession....");
			e.printStackTrace();
		}

	}

	@Override
	public void read() {
		try {
			if (Session.getInstance().checkPermission(Session.PERMISSION_READ)) {
				System.out.println("Reading...");
			} else {
				System.out.println("Current user have no permission for this operation");
			}
		} catch (SessionUninitializedException e) {
			System.out.println(
					"For file operations session must be initialized with a user via Session.startSession....");
			e.printStackTrace();
		}

	}

}
