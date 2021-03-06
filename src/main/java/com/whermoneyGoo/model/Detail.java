package com.whermoneyGoo.model;

public class Detail {
	private int detail_id;
	private String date;
	private String amount;
	private String description;
	private User user;
	
	public Detail(int detail_id, String date, String amount, String description, User user) {
		super();
		this.detail_id = detail_id;
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.user = user;
	}
	
	public Detail(String date, String amount, String description, User user) {
		super();
		this.date = date;
		this.amount = amount;
		this.description = description;
		this.user = user;
	}

	public Detail(int parseInt) {
		// TODO Auto-generated constructor stub
	}

	public Detail(int detail_id, String amount, String description) {
		super();
		this.detail_id = detail_id;
		this.amount = amount;
		this.description = description;	
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
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

	@Override
	public String toString() {
		return "Detail [detail_id=" + detail_id + ", date=" + date + ", amount=" + amount + ", description="
				+ description + ", user id=" + user.getUserId() + "]";
	}
	
	
}
