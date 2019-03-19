package com.ifpb.edu.model.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static String user;
	private static String password;
	private static String url;
	private static String driver;
	private static ConnectionFactory instance = null;
	private static Connection connection = null;
	
	private ConnectionFactory() {
	}
	
	public static ConnectionFactory getInstance() {
		if(instance == null) {
			synchronized (ConnectionFactory.class) {
				if(instance == null) {
					instance = new ConnectionFactory();
				}
			}
		}
		return instance;
	}
	
	static {
		Properties properties = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		try {
			properties.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		url = properties.getProperty("database.url");
		user = properties.getProperty("database.user");
		password = properties.getProperty("database.password");
		driver = properties.getProperty("database.driver");
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
//		Connection connection = null;
//		try {
//			Class.forName(driver);
//			connection = DriverManager.getConnection(url, user, password);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return connection;
	}
}
