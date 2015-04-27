package com.easybuy.user.domain;

import java.io.Serializable;

public class Seller extends User implements Serializable{

	private String email_id;
	private String address;
	private String phone_number;
	private String routing_number;
	private String account_number;
	private String status;
	
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getRouting_number() {
		return routing_number;
	}
	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
}
