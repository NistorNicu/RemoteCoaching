package com.remotecoaching.app.main;

public class MyFile implements File {

	@Override
	public void exists() {
		System.out.println("Checking if exists");
	}

	@Override
	public void write() {
		System.out.println("Writig...");
		
	}

	@Override
	public void read() {
		System.out.println("Reading...");
		
	}

	

	

}
