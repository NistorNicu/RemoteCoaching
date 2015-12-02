package com.remotecoaching.app.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MyDataSource {
	private static MyDataSource instance = new MyDataSource();
	private static Connection connection = null;
	private String url = "jdbc:mysql://localhost:3306/management_app_db";
	private String username = "root";
	private String password = "";
	private MyDataSource() {
	}
	
	
	public static MyDataSource getInstance(){
		return instance;
	}
	
	public Connection getConnection(){
		try {
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			connection = (Connection) DriverManager.getConnection (url, username, password);
			System.out.println("Conected");
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void diconnect(){
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
