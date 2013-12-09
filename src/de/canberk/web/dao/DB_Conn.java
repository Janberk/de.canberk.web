package de.canberk.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Conn {

	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/android_db";
		String username = "root";
		String password = "C@ny1981";
		Connection conn = null;

		try {
			System.out.println("Connecting database...");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect the database!", e);
		} 
		return conn;
	}

}
