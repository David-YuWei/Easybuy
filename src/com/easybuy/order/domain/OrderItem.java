package com.easybuy.order.domain;

public class OrderItem {

	private long order_id;
	private long product_id;
	private String product_name;
	private String image;
	private float price;
	private String user_name;
	private int quantity;
	private String status;
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Override
	public OrderItem clone(){
		OrderItem item = new OrderItem();
		item.setOrder_id(this.getOrder_id());
		item.setImage(this.getImage());
		item.setPrice(this.getPrice());
		item.setProduct_id(this.getProduct_id());
		item.setProduct_name(this.getProduct_name());
		item.setQuantity(this.getQuantity());
		item.setStatus(this.getStatus());
		item.setUser_name(this.getUser_name());
		return item;
	}
}
