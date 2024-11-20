package com.inventory.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	// Creates the connection between the database.
	// Input your personal url,username & password in the fields below.
	public static Connection connect() {
		Connection conn = null;
		String url = "";
		String username = "";
		String password = "";
		try {
			conn = DriverManager.getConnection(url, username, password);			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
