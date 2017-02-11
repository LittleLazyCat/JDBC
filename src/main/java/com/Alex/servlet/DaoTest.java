package com.Alex.servlet;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Alex.dao.ProductDao;
import com.Alex.dao.StudyDao;
import com.Alex.dao.UserDao;
import com.Alex.dao.imp.DaoFactory;
import com.Alex.dao.imp.ProductDaoImpl;
import com.Alex.dao.imp.StudyDaoFactory;
import com.Alex.dao.imp.StudyDaoImpl;
import com.Alex.domain.Product;
import com.Alex.domain.User;

/**
 * Servlet implementation class DaoTest
 */
public class DaoTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public DaoTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// User user = null;
		// UserDao userdao = DaoFactory.getInstance().getuserDaoImp();
		// user = userdao.getUser(1);
		// userdao.addUser(null);
		// userdao.download();
		// Product p = new Product();
		// String name = "XiaoMing";
		// p.setProductName("bag");
		// ProductDao proDao = new ProductDaoImpl();
		// proDao.buyProduct(p, name);
		StudyDao studyDao = StudyDaoFactory.getInstance().getStudyDaoImp();
		List list = new ArrayList();
		list =  studyDao.getCourseInfo("ZhangSan");
		StringBuffer sb = new StringBuffer();
		Iterator it = list.iterator();
			while (it.hasNext()) {
				
				sb.append( it.next()+ "<br>");

			}
		 
		response.setCharacterEncoding("utf8");
		response.getWriter().append(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
