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
	private static String DRIVER_NAME = "com.mysql.jdbc.Driver"; // ������
	private static String DB_URL = "jdbc:mysql://localhost:3306/test"; // URL
	private static String DB_USER_NAME = "root"; // �û���
	private static String DB_PASSWORD = "root"; // ����
	public static BasicDataSource ds = null;

	// ��ʼ�����ݿ����ӳ�
	public static void dbpoolInit() {
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

			// ��������
			conn = ds.getConnection();
			// �������
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

	public void buyProduct(Product p, String name) {
		dbpoolInit();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			// ��������
			conn = ds.getConnection();
			// ��������
			conn.setAutoCommit(false);
			// �������
			String sql = "insert into orders (Buyer,ProductName) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, p.getProductName());
			ps.execute();
			sql = "UPDATE product SET Inventory = Inventory -1 WHERE ProductName = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getProductName());
			ps.execute();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
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

	}

}
