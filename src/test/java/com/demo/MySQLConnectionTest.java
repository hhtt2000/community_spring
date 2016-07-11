package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class MySQLConnectionTest {

	private final String driver = "com.mysql.jdbc.Driver";
	private final String url = "jdbc:mysql://127.0.0.1:3306/community";
	private final String username = "jjh";
	private final String password = "password";
	
	@Test
	public void testConnection() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, username, password);
		System.out.println(con);
	}
}
