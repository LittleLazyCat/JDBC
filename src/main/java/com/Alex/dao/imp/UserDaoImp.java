package com.Alex.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Alex.dao.UserDao;
import com.Alex.dao.ex.RunTimeExcUser;
import com.Alex.domain.User;
import com.Alex.util.JdbcUtils;

public class UserDaoImp implements UserDao {

	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	public User getUser(int userId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user=null;
		try {
			// 建立连接
			conn = JdbcUtils.getConnection();
			// 创建连接
			String sql = "select USER_NAME,USER_PASSWORD,USER_EMAIL from t_user where USER_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
			user =  findUser(rs);
				
			}
		} catch (SQLException e) {
			throw new RunTimeExcUser("",e);
		}finally {
			try {
				JdbcUtils.free(rs, ps, conn);
			} catch (SQLException e) {
				throw new RunTimeExcUser("",e);
			}
			
		}
		return user;
	}

	private User findUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUser_Name(rs.getString("USER_NAME"));
		user.setUser_Psw(rs.getString("USER_PASSWORD"));
		user.setUser_Email(rs.getString("USER_EMAIL"));
		return user;
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

}
