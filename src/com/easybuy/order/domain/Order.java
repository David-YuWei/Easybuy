package com.easybuy.order.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.easybuy.shopcart.domain.ShopcartItem;

public class Order {

	private long order_id;
	private String user_name;
	private String shipping_options;
	private String full_name;
	private String address_1;
	private String address_2;
	private String phone;
	private float price;
	private float tax;
	private float shippingCost;
	private Date order_time;
	private String paypal_account;
	private String passcode;
	private String status;
	private List<OrderItem> items = new LinkedList<OrderItem>();
	
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getShipping_options() {
		return shipping_options;
	}
	public void setShipping_options(String shipping_options) {
		this.shipping_options = shipping_options;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getAddress_1() {
		return address_1;
	}
	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}
	public String getAddress_2() {
		return address_2;
	}
	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public String getPaypal_account() {
		return paypal_account;
	}
	public void setPaypal_account(String paypal_account) {
		this.paypal_account = paypal_account;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public void addItem(OrderItem item){
		this.items.add(item);
	}
	public float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public void clearItems(){
		items.clear();
	}
	
	@Override
	public Order clone(){
		Order order = new Order();
		order.setOrder_id(this.getOrder_id());
		order.setUser_name(this.getUser_name());
		order.setFull_name(this.getFull_name());
		order.setShipping_options(this.getShipping_options());
		order.setAddress_1(this.getAddress_1());
		order.setAddress_2(this.getAddress_2());
		order.setPhone(this.getPhone());
		order.setPrice(this.getPrice());
		order.setTax(this.getTax());
		order.setShippingCost(this.getShippingCost());
		order.setOrder_time(this.getOrder_time());
		order.setStatus(this.getStatus());
		order.setPaypal_account(this.getPaypal_account());
		order.setPasscode(this.getPasscode());
		//deep clone
		List<OrderItem> items_new = new LinkedList<OrderItem>();
		if(this.items.size() >0){
			for(OrderItem item:this.items){
				items_new.add(item.clone());
			}
		}
		order.setItems(items_new);
		return order;
	}
	
}
