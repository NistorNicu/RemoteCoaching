package com.remotecoaching.app.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.remotecoaching.app.exceptions.EntityNotFoundException;
import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.service.RoleService;

public class RoleServiceTest {
	static RoleService service;
	static Role expected;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new RoleService();
		expected = new Role();
		expected.setName("testRole");
	}
	
	@Before
	public void setUpBeforeTest() {
		Role someRole = null;
		try {
			someRole = service.getByName(expected.getName());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != someRole){
			service.delete(someRole);
		}
		
		
	}

	@Test
	public void testCreate() throws EntityNotFoundException {
		expected = service.create(expected);
		Role actual = service.get(expected.getId());
		assertEquals(expected, actual);
	}
	
	@Test(expected= EntityNotFoundException.class)
	public void testGet() throws EntityNotFoundException{
		expected = service.create(expected);
		Role actual =  service.get(expected.getId());
		assertEquals(expected, actual);
		service.get(-1);
	}
	
	@Test(expected= EntityNotFoundException.class)
	public void testGetByName() throws EntityNotFoundException{
		expected = service.create(expected);
		Role actual =  service.getByName(expected.getName());
		assertEquals(expected, actual);
		service.getByName("notValidName");
	}
	
	@Test
	public void testGetAll(){
		expected = service.create(expected);
		List<Role> feachedList =  service.getAll();
		assertTrue(feachedList.contains(expected));
	}
	
	@Test
	public void testUpdate() throws EntityNotFoundException{
		expected = service.create(expected);
		expected.setName("newName");
		service.update(expected);
		Role actual = service.get(expected.getId());
		assertEquals(expected, actual);
	}

	@Test(expected=EntityNotFoundException.class)
	public void testDelete() throws EntityNotFoundException{
		expected = service.create(expected);
		service.delete(expected.getId());
		service.get(expected.getId());
	}



}
