package com.Alex.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimpleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		String url = null;
		String user = null;
		String password = null;
		InputStream in = SimpleTest.class.getClassLoader().getResourceAsStream("jdbcconfig.properties");
		try {
			prop.load(in);
			url = prop.getProperty("studyDaoClass");
			user = prop.getProperty("username");
			password = prop.getProperty("password");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(url + " " + user + " " + password);
	}

}
