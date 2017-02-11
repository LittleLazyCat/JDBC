package com.Alex.dao.imp;

import java.io.InputStream;
import java.util.Properties;
//工厂方法以及单例模式
public class StudyDaoFactory {
	private static StudyDaoImpl studyDaoImpl=null;
	private static StudyDaoFactory instance = new StudyDaoFactory();
	private StudyDaoFactory(){
		try{
			 Properties prop = new Properties();
			 InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("jdbcconfig.properties");
			 prop.load(in);
			 String studyDaoClass = prop.getProperty("studyDaoClass");
			 Class clazz = Class.forName(studyDaoClass);
			 studyDaoImpl = (StudyDaoImpl)clazz.newInstance();
		 }catch (Exception e) {
			 throw new ExceptionInInitializerError(e);
		}
	}
	public static StudyDaoFactory getInstance(){
		 return instance;
	 }
	 public StudyDaoImpl getStudyDaoImp(){
		 return studyDaoImpl;
	 }
	
}
