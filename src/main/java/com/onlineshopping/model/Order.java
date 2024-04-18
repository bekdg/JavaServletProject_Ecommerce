package com.onlineshopping.model;

public class Order extends Product{
	private int orderId;
	private String username;
	private int quantity;
	private String date;
	private String status;
	
	public Order () {}

	public Order(int orderId, String username, int quantity, String date, String status) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.quantity = quantity;
		this.date = date;
		this.status = status;
	}

	public Order(String username, int quantity, String date) {
		super();
		this.username = username;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
