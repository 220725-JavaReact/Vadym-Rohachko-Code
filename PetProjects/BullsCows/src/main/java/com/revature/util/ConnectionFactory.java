package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
private static ConnectionFactory connectionFactory;
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private ConnectionFactory(){}	
	
	public static ConnectionFactory getInstance() {
		// lazy loading - create instance when it's needed
		if(connectionFactory == null) connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	
	public Connection getConnection() {
		Connection connie = null;
		try {
			connie = 
					DriverManager.getConnection(SecretClass.url, SecretClass.un, SecretClass.pw);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connie;
	}
}
