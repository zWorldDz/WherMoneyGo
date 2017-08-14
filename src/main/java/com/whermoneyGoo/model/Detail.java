package com.whermoneyGoo.model;

public class Detail {
	private int detail_id;
	private String date;
	private double amount;
	private String description;
	private User user;
		
	public Detail(int detail_id, String date, double amount, String description, User user) {
		super();
		this.detail_id = detail_id;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.user = user;
	}
	
		public Detail(int detail_id, String date) {
		super();
		this.detail_id = detail_id;
		this.date = date;
	}

	public int getDetail_id() {
		return detail_id;
	}
	public void setDetail_id(int detail_id) {
		this.detail_id = detail_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
