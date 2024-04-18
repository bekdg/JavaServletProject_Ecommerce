package com.onlineshopping.model;

public class User {
	private String username;
	private String first_name;
	private String last_name;
	private int age;
	private String email;
	private String customer_password;
	private String phone_number;
	private String address;
	
	public User() {
	}
	
	public User(String username, String first_name, String last_name, int age, String email, String customer_password,
			String phone_number, String address) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.email = email;
		this.customer_password = customer_password;
		this.phone_number = phone_number;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", first_name=" + first_name + ", last_name=" + last_name + ", age=" + age
				+ ", email=" + email + ", customer_password=" + customer_password + ", phone_number=" + phone_number
				+ ", address=" + address + ", getUsername()=" + getUsername() + ", getFirst_name()=" + getFirst_name()
				+ ", getLast_name()=" + getLast_name() + ", getAge()=" + getAge() + ", getEmail()=" + getEmail()
				+ ", getCustomer_password()=" + getCustomer_password() + ", getPhone_number()=" + getPhone_number()
				+ ", getAddress()=" + getAddress() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	 
	
}
	
