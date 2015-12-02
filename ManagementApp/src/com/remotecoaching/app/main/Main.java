package com.remotecoaching.app.main;

import java.util.ArrayList;
import java.util.List;

import com.remotecoaching.app.models.Group;
import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.persistence.GroupDataAccessObject;
import com.remotecoaching.app.persistence.MyDataSource;
import com.remotecoaching.app.persistence.RoleDataAccessObject;

public class Main {

	public static void main(String[] args) {
		RoleDataAccessObject roleDataAccessObject = new RoleDataAccessObject();
//		GroupDataAccessObject groupDataAccessObject = new GroupDataAccessObject();
//		List<Role> roles  = roleDataAccessObject.getAll();
//
//		Group group = new Group();
//		//group.setName("rollbacktest");
//		//group.setRoles(roles);
//		//groupDataAccessObject.create(group);
//		Group goup = groupDataAccessObject.get(6);
//		System.out.println(goup.toString());
//		System.out.println(groupDataAccessObject.getAll().toString());
		Role role = new Role(43, "testRole");
		roleDataAccessObject.create(null);
		roleDataAccessObject.delete(role);
		//roleDataAccessObject.create(role);
	}
	

}
