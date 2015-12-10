package com.remotecoaching.app.service;

import com.remotecoaching.app.exceptions.SessionUninitializedException;
import com.remotecoaching.app.main.MyFile;
import com.remotecoaching.app.main.Session;
import com.remotecoaching.app.models.Role;

public class FileService extends MyFile {
	public static final String PERMISSION_EXIST = "exist";
	public static final String PERMISSION_READ = "read";
	public static final String PERMISSION_WRITE = "write";

	@Override
	public void exists() {

		if (checkPermission(FileService.PERMISSION_EXIST)) {
			super.exists();
		} else {
			System.out.println("Current user have no permission for this operation");
		}

	}

	@Override
	public void write() {
		if (checkPermission(FileService.PERMISSION_WRITE)) {
			super.write();
		} else {
			System.out.println("Current user have no permission for this operation");
		}

	}

	@Override
	public void read() {
		if (checkPermission(FileService.PERMISSION_READ)) {
			super.read();
		} else {
			System.out.println("Current user have no permission for this operation");
		}

	}

	public boolean checkPermission(String permission) {
		try {
			for (Role r : Session.getInstance().getSessionRoles()) {
				if (r.getName().equals(permission)) {
					return true;
				}
			}
		} catch (SessionUninitializedException e) {
			System.out.println(
					"For file operations session must be initialized with a user via Session.startSession....");
			e.printStackTrace();
		}
		return false;
	}

}
