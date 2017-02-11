package com.Alex.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Alex.dao.StudyDao;
import com.Alex.dao.ex.RunTimeExcUser;
import com.Alex.util.JdbcUtils;

public class StudyDaoImpl implements StudyDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<String> list = null;

	public List<String> getCourseInfo(String name) {

		try {
			// 建立连接
			conn = JdbcUtils.getConnection();
			// 创建连接
			String sql = "select courseName from study where userName = ?";
			ps = conn.prepareStatement(sql);
			// 设置参数
			ps.setString(1, name);
			// 执行查询
			rs = ps.executeQuery();
			list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			throw new RunTimeExcUser(e.getMessage(), e);
		} finally {
			// 释放资源
			JdbcUtils.free(rs, ps, conn);

		}

	}

}
