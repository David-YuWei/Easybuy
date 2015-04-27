package com.easybuy.product.domain;

import java.io.Serializable;

public class Review implements Serializable {

	private String user_name;
	private long product_id;
	private float ranking;
	private String review;
	
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
	public float getRanking() {
		return ranking;
	}
	public void setRanking(float ranking) {
		this.ranking = ranking;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
