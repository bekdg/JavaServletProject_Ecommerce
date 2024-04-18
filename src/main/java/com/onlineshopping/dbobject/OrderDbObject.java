package com.onlineshopping.dbobject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.onlineshopping.model.Order;
import com.onlineshopping.model.Product;

public class OrderDbObject {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDbObject(Connection con) {
		this.con = con;
	}

	public boolean insertOrder(Order model) {
		boolean result = false;

		try {

			query = "insert into orders (product_id, customer_username, order_date, quantity) values(?, ?, ?, ?)";
			
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setString(2, model.getUsername());
			pst.setString(3, model.getDate());
			pst.setInt(4, model.getQuantity());
			pst.executeUpdate();
			
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public List<Order> userOrders(String username){
		List<Order> list = new ArrayList<>();
		
		try {
			
			query = "SELECT o.order_id, p.product_id, p.product_name,  c.catagory_name, o.quantity, p.price * o.quantity AS total_price, o.order_date, o.status\r\n"
					+ "from orders o JOIN product p ON o.product_id = p.product_id \r\n"
					+ "JOIN catagory c ON p.catagory_id = c.catagory_id WHERE o.customer_username=?\r\n"
					+ "ORDER BY order_id DESC";
			pst = this.con.prepareStatement(query);
			pst.setString(1, username);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				//ProductDbObject productDb = new ProductDbObject(this.con);			
				
				//int productId = rs.getInt("product_id");
				//Product product = productDb.getSingleProduct(productId);
				
				order.setOrderId(rs.getInt("order_id"));
				order.setId(rs.getInt("product_id"));
				order.setName(rs.getString("product_name"));
				order.setCategory(rs.getString("catagory_name"));
				order.setPrice(rs.getFloat("total_price"));
				order.setQuantity(rs.getInt("quantity"));
				order.setDate(rs.getString("order_date"));
				order.setStatus(rs.getString("status"));
				list.add(order);
	
		}

		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void cancelOrder(int id) {
		try {
			query = "delete from orders where order_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<Order> getAllOrders(){
		List<Order> orders = new ArrayList<>();
		try {
			
			query = "SELECT o.order_id, o.order_date, o.customer_username, p.product_name, o.quantity, o.quantity * p.price AS total_price, o.status "
					+ "FROM orders o JOIN product p ON o.product_id = p.product_id ORDER BY o.status, o.order_id";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setDate(rs.getString("order_date"));
				order.setUsername(rs.getString("customer_username"));
				order.setName(rs.getString("product_name"));
				order.setPrice(rs.getFloat("total_price"));
				order.setQuantity(rs.getInt("quantity"));
				order.setStatus(rs.getString("status"));
				
				orders.add(order);
			}
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public boolean shipOrder(int order_id) {
		boolean success = false;
		
		try {
			
			query = "UPDATE orders SET status = ? WHERE order_id = ?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, "shipped");
			pst.setInt(2, order_id);
			pst.executeUpdate();
			success = true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return success;
	}
}
