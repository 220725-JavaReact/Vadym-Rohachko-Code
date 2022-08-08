package Util;

import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionFactory {
	//Create Singleton
	private static ConnectionFactory connectionFactory;
	
	//Check if there is the driver
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Private constructor
	private ConnectionFactory() {}
	
	public static ConnectionFactory getInstance() {
		//Lazy loading creates the instance only when needed
		if(connectionFactory == null) connectionFactory = new ConnectionFactory();
		return connectionFactory;
	}
	
	public Connection getConnection() {
		Connection connie = null;
		try {
		connie = DriverManager.getConnection(SecretClass.url, SecretClass.userName, SecretClass.password);
		return connie;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connie;
	}
	
}
