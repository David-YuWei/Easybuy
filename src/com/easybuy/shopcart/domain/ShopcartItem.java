package com.easybuy.shopcart.domain;

public class ShopcartItem {
	
	private long product_id;
	private String user_name_buyer;
	private String user_name_seller;
	private String product_name;
	private float price;
	private int quantity;
	private String image;
	
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getUser_name_buyer() {
		return user_name_buyer;
	}
	public void setUser_name_buyer(String user_name_buyer) {
		this.user_name_buyer = user_name_buyer;
	}
	public String getUser_name_seller() {
		return user_name_seller;
	}
	public void setUser_name_seller(String user_name_seller) {
		this.user_name_seller = user_name_seller;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
