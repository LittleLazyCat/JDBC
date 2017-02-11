package com.mybatis.inte;

import com.mybatis.domain.Product;
import com.mybatis.domain.User;

public interface Op {
	public User getUser(int id);
	public Product getProduct(int id);
}
