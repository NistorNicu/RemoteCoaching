package com.remotecoaching.app.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.remotecoaching.app.models.User;
import com.remotecoaching.app.persistence.DataAccessObjectGenericInterface;
import com.remotecoaching.app.persistence.UserDataAccessObject;

public class UserService extends DataServiceGenericImplementation<User, Integer>  {
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public UserService() {
		super(new UserDataAccessObject());
	}
	
	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}



}
