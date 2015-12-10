package com.remotecoaching.app.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.remotecoaching.app.main.Session;
import com.remotecoaching.app.models.Role;
import com.remotecoaching.app.service.FileService;

public class FileServiceTest {
	
	static Set<Role> roles = new HashSet<>();
	
	@BeforeClass
	public static void setUpClass(){
		roles.add(new Role(1, FileService.PERMISSION_EXIST));
	}
	

	@Test
	public void test() {
		
		
		Session session = Mockito.mock(Session.class);
		Mockito.when(session.getSessionRoles()).thenReturn(roles);
		FileService fileService = new FileService();
		fileService.read();
		
	}

}
