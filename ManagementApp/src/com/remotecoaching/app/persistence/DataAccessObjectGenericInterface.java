package com.remotecoaching.app.persistence;

import java.io.Serializable;
import java.util.List;

import com.remotecoaching.app.exceptions.EntityNotFoundException;

public interface DataAccessObjectGenericInterface<T, K extends Serializable> {

	T create(T newInstance);

	T get(K id) throws EntityNotFoundException;
	
	T getByName(String name) throws EntityNotFoundException;

	List<T> getAll();

	void update(T updatedInstance);

	void delete(T instanceToDelete);

	void delete(K instanceToDeleteID);
}
