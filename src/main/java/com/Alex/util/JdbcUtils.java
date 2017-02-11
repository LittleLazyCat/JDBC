package com.Alex.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.Alex.dao.ex.RunTimeExcUser;


public final class JdbcUtils {

	private static String url = "jdbc:mysql://localhost:3306/netease";
	private static String user = "root";
	private static String password = "root";
	private static DataSource myDataSource = null;

	private JdbcUtils() {
	}
//	static {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// myDataSource = new MyDataSource2();
			Properties prop = new Properties();
			// prop.setProperty("driverClassName", "com.mysql.jdbc.Driver");
			// prop.setProperty("user", "user");

//			InputStream is = JdbcUtils.class.getClassLoader()
//					.getResourceAsStream("dbcpconfig.properties");
			InputStream is = JdbcUtils.class.getClassLoader()
					.getResourceAsStream("jdbcconfig.properties");
			prop.load(is);
			myDataSource = org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DataSource getDataSource() {
		return  myDataSource;
	}
	

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet rs, Statement st, Connection conn){
		try {
			if (rs != null) {
				rs.close();
			}
		}catch (SQLException e) { 
			throw new RunTimeExcUser(e.getMessage(), e);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			}catch (SQLException e) { 
				throw new RunTimeExcUser(e.getMessage(), e);
			} finally {
				try {
					if (conn != null) {
					conn.close();
				}
				} catch (SQLException e) { 
					throw new RunTimeExcUser(e.getMessage(), e);
				}
				

			}
		}
	}
}
