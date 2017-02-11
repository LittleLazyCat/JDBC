package com.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.domain.Product;
import com.mybatis.inte.Op;

public class FindProduct {
	public Product getProduct(int id) {
		// 1.���������ļ�
		String resource = "conf.xml";
		// 2.����Ӧ�������ļ�
		InputStream is = FindUser.class.getClassLoader().getResourceAsStream(resource);
		// 3.����SqlSessionFactory
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
		// 4.openSession()������ΪTRUE������Զ��ύ
		SqlSession session = ssf.openSession(true);
		Product pro= null;
		try {
			// 5.��ȡ������
			Op op = session.getMapper(Op.class);
			// ��ѯ�û�
			pro = op.getProduct(id);
			return pro;
		} finally {
			session.close();
			
		}
	}
}
