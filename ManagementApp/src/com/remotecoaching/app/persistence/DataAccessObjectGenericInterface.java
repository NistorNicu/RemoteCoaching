package com.remotecoaching.app.persistence;

import java.io.Serializable;
import java.util.List;

public interface DataAccessObjectGenericInterface<T, K extends Serializable> {
	
	void create(T newInstance);
	T get(K id);
	List<T> getAll();
	void update(T updatedInstance);
	void delete(T instanceToDelete);
	void delete(K instanceToDeleteID);
}
