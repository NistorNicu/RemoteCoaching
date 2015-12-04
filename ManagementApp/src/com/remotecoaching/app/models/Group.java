package com.remotecoaching.app.models;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private int id;
	private String name;
	private List<Role> roles;

	public Group() {
		roles = new ArrayList<>();
	}

	public Group(int id) {
		super();
		this.id = id;
	}

	public Group(int id, String name, List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", roles=" + roles + "]";
	}

}
