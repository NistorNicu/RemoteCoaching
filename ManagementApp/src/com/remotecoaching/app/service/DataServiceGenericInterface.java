package com.remotecoaching.app.service;

import java.io.Serializable;

import com.remotecoaching.app.persistence.DataAccessObjectGenericInterface;

public interface DataServiceGenericInterface<T, K extends Serializable> extends DataAccessObjectGenericInterface<T,K > {
	
	
	
}
