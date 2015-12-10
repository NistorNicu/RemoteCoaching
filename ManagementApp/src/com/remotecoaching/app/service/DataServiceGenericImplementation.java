package com.remotecoaching.app.service;

import java.io.Serializable;
import java.util.List;

import com.remotecoaching.app.exceptions.EntityNotFoundException;
import com.remotecoaching.app.persistence.DataAccessObjectGenericInterface;

public class DataServiceGenericImplementation <T, K extends Serializable>  implements DataServiceGenericInterface<T, K>{

	
	private DataAccessObjectGenericInterface<T, K> dataAccessObject;

	public DataServiceGenericImplementation(DataAccessObjectGenericInterface<T, K> dataAccessObject) {
		this.dataAccessObject = dataAccessObject;
	}
	
	@Override
	public T create(T newInstance) {
		return dataAccessObject.create(newInstance);
	}

	@Override
	public T get(K id) throws EntityNotFoundException {
		return dataAccessObject.get(id);
	}

	@Override
	public T getByName(String name) throws EntityNotFoundException {
		return dataAccessObject.getByName(name);
	}

	@Override
	public List<T> getAll() {
		return dataAccessObject.getAll();
	}

	@Override
	public void update(T updatedInstance) {
		dataAccessObject.create(updatedInstance);
		
	}

	@Override
	public void delete(T instanceToDelete) {
		dataAccessObject.delete(instanceToDelete);
		
	}

	@Override
	public void delete(K instanceToDeleteID) {
		dataAccessObject.delete(instanceToDeleteID);
		
	}

}
