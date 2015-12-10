package com.remotecoaching.app.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.remotecoaching.app.exceptions.EntityNotFoundException;
import com.remotecoaching.app.models.Group;
import com.remotecoaching.app.models.User;
import com.remotecoaching.app.service.GroupService;
import com.remotecoaching.app.service.UserService;

public class UserServiceTest {

	static UserService service;
	static User expected;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new UserService();
		expected = new User();
		expected.setUserName("testRole");
	}

	@Before
	public void setUpBeforeTest() {
		User someRole = null;
		try {
			someRole = service.getByName(expected.getUserName());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != someRole) {
			service.delete(someRole);
		}

	}

	@Test
	public void testCreate() throws EntityNotFoundException {
		expected = service.create(expected);
		User actual = service.get(expected.getId());
		assertEquals(expected, actual);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testGet() throws EntityNotFoundException {
		expected = service.create(expected);
		User actual = service.get(expected.getId());
		assertEquals(expected, actual);
		service.get(-1);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testGetByName() throws EntityNotFoundException {
		expected = service.create(expected);
		User actual = service.getByName(expected.getUserName());
		assertEquals(expected, actual);
		service.getByName("notValidName");
	}

	@Test
	public void testGetAll() {
		expected = service.create(expected);
		List<User> feachedList = service.getAll();
		assertTrue(feachedList.contains(expected));
	}

	@Test
	public void testUpdate() throws EntityNotFoundException {
		expected = service.create(expected);
		expected.setUserName("newName");
		service.update(expected);
		User actual = service.get(expected.getId());
		assertEquals(expected, actual);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDelete() throws EntityNotFoundException {
		expected = service.create(expected);
		service.delete(expected.getId());
		service.get(expected.getId());
	}


}
