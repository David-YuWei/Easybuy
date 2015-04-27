package com.easybuy.wishlist.domain;

import java.io.Serializable;

public class WishlistItem implements Serializable {

	private String user_name;
	private long product_id;
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	
	
}
