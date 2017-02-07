package com.Alex.dao;

import com.Alex.domain.Product;

public interface ProductDao {
public abstract Product getProduct(Product p);
public abstract void buyProduct(Product p,String name);
}
