package com.mybatis.domain;

import java.util.List;

public class User {
	private int id;
	private String userName;
	private String tel;
	private List<Product> products;
	
	public User(Integer id, String userName, String tel) {
		this.id = id;
		this.userName = userName;
		this.tel = tel; 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", tel=" + tel + ", products=" + products + "]";
	}
	
}
