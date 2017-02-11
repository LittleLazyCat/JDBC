package com.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.domain.Product;
import com.mybatis.inte.Op;

public class FindProduct {
	public Product getProduct(int id) {
		// 1.声明配置文件
		String resource = "conf.xml";
		// 2.加载应用配置文件
		InputStream is = FindUser.class.getClassLoader().getResourceAsStream(resource);
		// 3.创建SqlSessionFactory
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		// 4.openSession()方法设为TRUE，则会自动提交
		SqlSession session = ssf.openSession(true);
		Product pro= null;
		try {
			// 5.获取操作类
			Op op = session.getMapper(Op.class);
			// 查询用户
			pro = op.getProduct(id);
			return pro;
		} finally {
			session.close();
			
		}
	}
}
