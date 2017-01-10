package com.Alex.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtils {

	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String user = "root";
	private static String password = "root";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void free(ResultSet rs, Statement st, Connection conn) throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} finally {

				if (conn != null) {
					conn.close();
				}

			}
		}
	}
}
