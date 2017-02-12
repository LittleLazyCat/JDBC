package com.Alex.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Alex.dao.StudyDao;
import com.Alex.dao.ex.RunTimeExcUser;
import com.Alex.util.JdbcUtils;

public class StudyDaoImpl implements StudyDao {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Map<String, Object>> list = null;

	public List<Map<String, Object>> getCourseInfo() {

		try {
			// 建立连接
			conn = JdbcUtils.getConnection();
			// 创建连接
			String sql = "select userName,courseName from study";
			ps = conn.prepareStatement(sql);
			//设置setFetchSize()方法的值
			//先读取一条记录，处理结束后再处理下一条记录
			ps.setFetchSize(1);
			rs = ps.executeQuery();
			list = new ArrayList<Map<String,Object>>();
			Map<String, Object> map = null;
			while(rs.next()){
				map = new HashMap<String,Object>();
				map.put("userName", rs.getString(1));
				map.put("courseName", rs.getString(2));
				list.add(map);
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
