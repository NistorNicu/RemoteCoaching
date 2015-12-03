package com.remotecoaching.app.main;

import com.remotecoaching.app.exceptions.SessionUninitializedException;
import com.remotecoaching.app.models.Role;

public class MyFile implements File {

	@Override
	public void exists() {
		if(checkPermission("exist")){
			System.out.println("Checking if exists");
		}else{
			System.out.println("Current user have no permission for this operation");
		}

	}

	@Override
	public void write() {
		if(checkPermission("write")){
			System.out.println("Writing...");
		}else{
			System.out.println("Current user have no permission for this operation");
		}


	}

	@Override
	public void read() {
		if(checkPermission("read")){
			System.out.println("Reading...");
		}else{
			System.out.println("Current user have no permission for this operation");
		}


	}
	
	private boolean checkPermission(String permission){
		boolean ok = false;
		try {
			for(Role r :  Session.getInstance().getSessionRoles()){
				if (r.getName().equals(permission)){
					return true;
				}
			}
		} catch (SessionUninitializedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return ok;
	}

}
