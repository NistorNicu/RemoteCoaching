package com.remotecoaching.app.service;

import com.remotecoaching.app.models.Group;
import com.remotecoaching.app.persistence.GroupDataAccessObject;

public class GroupService extends DataServiceGenericImplementation<Group, Integer> {

	public GroupService() {
		super(new GroupDataAccessObject());
	}

}
