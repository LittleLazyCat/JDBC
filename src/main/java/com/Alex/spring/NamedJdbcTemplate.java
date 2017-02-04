package com.Alex.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.Alex.util.JdbcUtils;

import com.Alex.domain.User;

/**
 * Servlet implementation class NamedJdbcTemplate
 */
public class NamedJdbcTemplate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static NamedParameterJdbcTemplate named = new NamedParameterJdbcTemplate(
			JdbcUtils.getDataSource());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NamedJdbcTemplate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserId(1);
		user = findUser1(user);
		//不设置编码会造成显示为‘？’
		response.setCharacterEncoding("GBK");
		response.getWriter().append(user.toString());
	}
	static void addUser(User user) {
		String sql = "insert into t_user(user_id,user_name,user_password,user_email) values (:userId,:userName,:userPassword,:userEmail) ";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		named.update(sql, ps, keyHolder);
		int id = keyHolder.getKey().intValue();
		user.setUserId(id);
		
//		Map map = keyHolder.getKeys();
	}

	static User findUser(User user) {
		String sql = "select user_id as userId,user_name as userName,user_password as userPsw,user_email as userEmail  from t_user "
				+ "where user_id = :id";
		Map params = new HashMap();
		// params.put("n", user.getName()); 
		params.put("id", user.getUserId());
		Object u = named.queryForObject(sql, params, new BeanPropertyRowMapper(
				User.class));
		return (User) u;
	}

	static User findUser1(User user) {
		String sql = "select user_id as userId,user_name as userName,user_password as userPsw,user_email as userEmail  from t_user "
				+ "where user_id = :userId";
		SqlParameterSource ps = new BeanPropertySqlParameterSource(user);
		Object u = named.queryForObject(sql, ps, new BeanPropertyRowMapper(
				User.class));
		return (User) u;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
