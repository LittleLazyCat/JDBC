package com.Alex.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.Alex.dao.ex.RunTimeExcUser;
import com.Alex.dao.imp.DaoFactory;

public final class JdbcUtils {

	private static DataSource myDataSource = null;
	private static BasicDataSource ds = null;
	private static Properties prop = null;
 
	static {
		try {

			prop = new Properties();
			InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			prop.load(is);
			String DriverName = prop.getProperty("driverClassName");
			//注册驱动
			Class.forName(DriverName);
			myDataSource = org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static DataSource getDataSource() {
		return myDataSource;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			// 获取参数
			prop = new Properties();
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbcconfig.properties");
			prop.load(in);
			String DriverName = prop.getProperty("driverClassName");
			String url = prop.getProperty("url");
			String user = prop.getProperty("username");
			String password = prop.getProperty("password");
			ds = new BasicDataSource();
			ds.setDriverClassName(DriverName);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(password);
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RunTimeExcUser(e.getMessage(), e);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
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
