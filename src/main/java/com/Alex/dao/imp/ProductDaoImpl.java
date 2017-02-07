package com.Alex.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.Alex.dao.ProductDao;
import com.Alex.domain.Product;

public class ProductDaoImpl implements ProductDao {
	private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost:3306/test";
	private static String DB_USER_NAME = "root";
	private static String DB_PASSWORD = "root";
	public static BasicDataSource ds = null;
	
	public static void dbpoolInit(){
		ds = new BasicDataSource();
		ds.setUrl(DB_URL);
		ds.setDriverClassName(DRIVER_NAME);
		ds.setUsername(DB_USER_NAME);
		ds.setPassword(DB_PASSWORD);
	}
	
	public Product getProduct(Product p) {
		dbpoolInit();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			// 建立连接
			conn = ds.getConnection();
			// 创建语句
			String sql = "select Id,ProductName,Inventory from product where id =?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				p.setProductName(rs.getString("ProductName"));
				p.setInventory(rs.getInt("Inventory"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {

				try {
					if (ps != null) {
						ps.close();
					}
				} catch (SQLException e) {

					e.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}

		return p;
	}

}
