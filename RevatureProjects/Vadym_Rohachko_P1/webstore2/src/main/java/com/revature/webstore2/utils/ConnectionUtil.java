package com.revature.webstore2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContext;

public class ConnectionUtil {

	private static Connection con;
	
	private ConnectionUtil() {
		con = null;
	}
	
	// This method will be designed to give me a connection object
	public static Connection getConnection() {
		
		// Determine if we already have an existing connection, if so just return that
		try {

			if (con != null && !con.isClosed()) {
				return con;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}	

		try {
			Class.forName("org.postgresql.Driver");	
			String url, user, pass;
			Properties prop = new Properties();	
			
			// Relative
			//folder src/main/resourses  must be in pluaral form and located in src/main/
			prop.load(ConnectionUtil.class.getResourceAsStream("/database.properties"));	
			
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			pass = prop.getProperty("pass");

			con = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
}