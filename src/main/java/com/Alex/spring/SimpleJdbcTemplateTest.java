package com.Alex.spring;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.Alex.domain.User;

import com.Alex.util.JdbcUtils;
import com.Alex.domain.User;

/**
 * Servlet implementation class SimpleJdbcTemplateTest
 */
public class SimpleJdbcTemplateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SimpleJdbcTemplate simple = new SimpleJdbcTemplate(JdbcUtils
			.getDataSource());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleJdbcTemplateTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User u = find();
		response.setCharacterEncoding("GBK");
		response.getWriter().append(u.toString());
	}
	static User find() {
		String sql = "select user_id as userId,user_name as userName,user_password as userPsw,user_email as userEmail  from t_user "
				+ "where user_id = ?";
		User user = simple.queryForObject(sql,
				ParameterizedBeanPropertyRowMapper.newInstance(User.class),
				6);
		return user;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
