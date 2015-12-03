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
		RoleDataAccessObject roleDataAccessObject = new RoleDataAccessObject();
		GroupDataAccessObject groupDataAccessObject = new GroupDataAccessObject();
		UserDataAccessObject userDataAccessObject = new UserDataAccessObject();
//		List<Role> roles  = roleDataAccessObject.getAll();
//
//		List<User> userList = userDataAccessObject.getAll();
//		System.out.println(userList);
//		for (User u : userList){
//			System.out.println(userDataAccessObject.get(u.getId()).toString());
//		}
//		User user = new User();
//		User user2 = userList.get(0);
//		User user3 = userList.get(2);
//		user3.setEmail("email1243");
//		user3.setUserName("Gigel");
//		userDataAccessObject.create(user3);
//		userDataAccessObject.create(user2);
//		userDataAccessObject.create(user);
//		user2.setUserName("");
//		userDataAccessObject.update(user2);
//		userDataAccessObject.delete(user2.getId());
//		
//		List<Group> allGroups = groupDataAccessObject.getAll();
//		System.out.println(allGroups);
//		Group group = new Group();
//		group.setName("Manageri3");
//		group.getRoles().addAll(roleDataAccessObject.getAll());
//		//group.setRoles(roles);
//		groupDataAccessObject.create(group);
//		Group group2 = new Group();
//		groupDataAccessObject.create(group2);
//		Group group3 = groupDataAccessObject.get(1);
//		group3.setName("");
//		groupDataAccessObject.update(group3);
//		group3.setName("Manageri3");
//		groupDataAccessObject.update(group3);
//		
//		
//		group3 = groupDataAccessObject.get(1);
//		System.out.println(group3.getRoles().toString());
//		group3.setRoles(roleDataAccessObject.getAll());
//		groupDataAccessObject.update(group3);
//		group3 = groupDataAccessObject.get(1);
//		System.out.println(group3.getRoles().toString());
//		group3.getRoles().remove(1);
//		groupDataAccessObject.update(group3);
//		group3 = groupDataAccessObject.get(1);
//		System.out.println(group3.getRoles().toString());
//		group3.getRoles().remove(0);
//		group3.getRoles().add(roleDataAccessObject.get(10));
//		groupDataAccessObject.update(group3);
//		group3 = groupDataAccessObject.get(1);
//		System.out.println(group3.getRoles().toString());
//		groupDataAccessObject.delete(1);
//		System.out.println(groupDataAccessObject.get(1));
//		System.out.println(groupDataAccessObject.get(1232));
//		Group goup = groupDataAccessObject.get(6);
//		System.out.println(goup.toString());
//		System.out.println(groupDataAccessObject.getAll().toString());
//		Role role = new Role(71, "testRole");
//		roleDataAccessObject.create(role);
//		role.setName("newNAME");
//		roleDataAccessObject.update(role);
//		Role role2 = roleDataAccessObject.get(role.getId());
//		System.out.println(role2);
//		List<Role> roleList = roleDataAccessObject.getAll();
//		System.out.println(roleList.toString());
//		for (Role r : roleList){
//			System.out.println(roleDataAccessObject.get(r.getId()).toString());
//		}
//		System.out.println(roleDataAccessObject.get(role.getId()).toString());
		//roleDataAccessObject.delete((Role)null);
		//roleDataAccessObject.create(role);
	}
	

}
