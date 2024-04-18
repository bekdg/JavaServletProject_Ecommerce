package com.onlineshopping.dbobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.cj.exceptions.RSAException;
import com.onlineshopping.model.Cart;
import com.onlineshopping.model.Product;

public class ProductDbObject {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDbObject(Connection con) {
		this.con = con;
	}
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<Product>();
		
		try {
			query = "SELECT p.product_id, c.catagory_name, p.product_name, p.price, p.image_url, p.product_description, p.quantity "
					+ "FROM product p JOIN catagory c ON p.catagory_id = c.catagory_id ORDER BY product_id DESC";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("product_id"));
				row.setName(rs.getString("product_name"));
				row.setCategory(rs.getString("catagory_name"));
				row.setDescription(rs.getString("product_description"));
				row.setPrice(rs.getFloat("price"));
				row.setImage_url(rs.getString("image_url"));
				row.setQuantity(rs.getInt("quantity"));
				
				products.add(row);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return products;
		
	}
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size() > 0) {
				for (Cart item: cartList) {
					query = "SELECT p.product_id, c.catagory_name, p.product_name, p.price, p.image_url, p.product_description, p.quantity "
							+ "FROM product p JOIN catagory c ON p.catagory_id = c.catagory_id WHERE p.product_id = ?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()){
						Cart row = new Cart();
						row.setId(rs.getInt("product_id"));
						row.setName(rs.getString("product_name"));
						row.setCategory(rs.getString("catagory_name"));
						row.setPrice(rs.getFloat("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if (cartList.size() > 0) {
				for (Cart c: cartList) {
					query = "select price from product where product_id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					
					while (rs.next()) {
						sum+=rs.getFloat("price")*c.getQuantity();
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	public Product getSingleProduct(int id) {
		Product product=null; 
		try {
			
			query = "select * from product where product_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setName(rs.getString("product_name"));
				product.setCategory("Unknown");
				product.setPrice(rs.getFloat("price"));
				product.setImage_url(rs.getString("image_url"));
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}
	public boolean postProduct(Product product) {
		boolean success = false;
		
		try {
			query = "INSERT INTO product (catagory_id, product_name, price, image_url, product_description, quantity) VALUES (?, ?, ?, ?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(product.getCategory()));
			pst.setString(2, product.getName());
			pst.setFloat(3, product.getPrice());
			pst.setString(4, product.getImage_url());
			pst.setString(5, product.getDescription());
			pst.setInt(6, product.getQuantity());
			pst.executeUpdate();
			
			success = true;
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public boolean deleteProduct(int product_id) {
		boolean success = false;
		
		try {
			
			query = "delete from product where product_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, product_id);
			pst.execute();
			
			success = true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
	public List<Product> searchProduct(String searchQuery){
		List<Product> result = new ArrayList<>();

		try {
		query = "SELECT p.product_id, c.catagory_name, p.product_name, p.price, p.image_url, p.product_description, p.quantity\r\n"
				+ "FROM product p JOIN catagory c ON p.catagory_id = c.catagory_id \r\n"
				+ "WHERE p.product_name LIKE ? OR p.product_description LIKE ? ORDER BY product_id DESC";

		
		pst = this.con.prepareStatement(query);
		pst.setString(1, "%" + searchQuery + "%");
		pst.setString(2, "%" + searchQuery + "%");
		rs = pst.executeQuery();
		
		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("product_id"));
			product.setName(rs.getString("product_name"));
			product.setCategory(rs.getString("catagory_name"));
			product.setDescription(rs.getString("product_description"));
			product.setPrice(rs.getFloat("price"));
			product.setImage_url(rs.getString("image_url"));
			product.setQuantity(rs.getInt("quantity"));
			
			result.add(product);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
