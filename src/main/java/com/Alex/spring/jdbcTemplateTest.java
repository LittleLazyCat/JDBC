package com.Alex.spring;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.Alex.domain.User;

import com.Alex.util.JdbcUtils;;;

/**
 * Servlet implementation class jdbcTemplateTest
 */
public class jdbcTemplateTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JdbcTemplate jdbc = new JdbcTemplate((DataSource) JdbcUtils.getDataSource());   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jdbcTemplateTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user =new User();
		user.setUserName("ÊØ×¾");
		user.setUserEmail("10417@qq.com");
		user.setUserPsw("00000");
		addUser(user);
		response.getWriter().append("Served at: ").append(user.toString());
	}

	static int addUser(final User user) {
		jdbc.execute(new ConnectionCallback<Object>() {

			public Object doInConnection(Connection con) throws SQLException,
					DataAccessException {
				String sql = "insert into t_user(USER_ID,USER_NAME,USER_PASSWORD, USER_EMAIL) values (?,?,?,?) ";
				PreparedStatement ps = con.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS);
				ps.setObject(1,null); 
				ps.setString(2, user.getUserName()); 
				ps.setString(3, user.getUserPsw());
				ps.setString(4, user.getUserEmail());
				ps.executeUpdate();

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					user.setUserId(rs.getInt(1));
				return null;
			}
		});
		return 0;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
