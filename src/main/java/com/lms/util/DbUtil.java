package com.lms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private static final String url = "jdbc:mysql://localhost:3306/library_db?useSSL=false";
	private static final String username = "MYSQL KULLANICI ADINIZI YAZIN.";
	private static final String password = "MYSQL ŞİFRENİZİ YAZIN.";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url, username, password);
	}
}
