package com.Alex.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Alex.util.JdbcUtils;

public class Crud{

	static void read() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 建立连接
			conn = JdbcUtils.getConnection();
			// 创建连接
			String sql = "select USER_NAME,USER_PASSWORD from t_user where USER_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("USER_NAME") + "\t" + rs.getString("USER_PASSWORD"));
			}
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
}
