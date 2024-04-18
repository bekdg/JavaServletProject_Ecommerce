package com.onlineshopping.model;

public class Product {
	private int id;
	private String name;
	private float price;
	private String description;
	private String category;
	private String image_url;
	private int quantity;
	
	
	
	public Product() {
		
	}

	public Product(int id, String name, float price, String description, String image_url, int quantity, String category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image_url = image_url;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	

	
	
	
	
	
	
	
	
}
