package com.Alex.domain;

public class Product {
private int id;
private String productName;
private int Inventory;
public int getInventory() {
	return Inventory;
}
public void setInventory(int inventory) {
	Inventory = inventory;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
@Override
public String toString() {
	return "Product [productName=" + productName + ", Inventory=" + Inventory + "]";
}


}
