package com.onlineshopping.dbobject;

import java.sql.Connection;

import com.onlineshopping.model.User;

import java.sql.*;

public class UserDbObject {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public UserDbObject(Connection con) {
		this.con = con;
	}
	
	public User userLogin (String username,String customer_password) {
		User user= null;
		try {
			query="select *from Customer where username=? and customer_password=?";
			pst=this.con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, customer_password);
			rs=pst.executeQuery();

			if(rs.next()) {
				user=new User();
				user.setUsername(rs.getString("username"));
				user.setFirst_name(rs.getString("first_name"));
				user.setLast_name(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				user.setEmail(rs.getString("email"));
				user.setPhone_number(rs.getString("phone_number"));
				user.setAddress(rs.getString("Address"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		
		return user; 
	}
	
	
	public boolean registerUser (User user) {
		boolean inserted = false;
		try {
			
			query = "insert into customer values(?, ?, ?, ?, ?, ?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1,user.getUsername());
			pst.setString(2, user.getFirst_name());
			pst.setString(3, user.getLast_name());
			pst.setInt(4, user.getAge());
			pst.setString(5, user.getEmail());
			pst.setString(6, user.getCustomer_password());
			pst.setString(7, user.getPhone_number());
			pst.setString(8, user.getAddress());
			
			pst.executeUpdate();
			inserted = true;
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return inserted;
	}
	
	
	
	public boolean validUsername(String username) {
		boolean isValid = true;
		try {
			
			query = "select * from customer where username=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				isValid=false;
			}
			
		}catch (Exception e) {
		e.printStackTrace();
		}
		return isValid;
	}
	
}
