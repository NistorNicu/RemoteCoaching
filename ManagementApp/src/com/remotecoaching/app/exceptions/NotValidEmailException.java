package com.remotecoaching.app.exceptions;

public class NotValidEmailException extends Exception {
	private static String msg = "Not valid email";
	
	public NotValidEmailException(){
		super(msg);
	}
}
