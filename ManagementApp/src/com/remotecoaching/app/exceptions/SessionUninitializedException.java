package com.remotecoaching.app.exceptions;

public class SessionUninitializedException extends Exception {
	private static String msg = "Uninitialized session , before use call Session.startSession....";

	public SessionUninitializedException() {
		super(msg);
	}

}
