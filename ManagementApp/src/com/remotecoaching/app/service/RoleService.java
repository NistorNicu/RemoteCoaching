package com.remotecoaching.app.service;

import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.persistence.RoleDataAccessObject;

public class RoleService extends DataServiceGenericImplementation<Role, Integer> {

	public RoleService() {
		super(new RoleDataAccessObject());
	}

}
