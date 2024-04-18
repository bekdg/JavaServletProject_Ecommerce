package com.onlineshopping.dbobject;

import com.onlineshopping.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDbObject {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public CategoryDbObject (Connection con) {
		this.con = con;
}
	
	public List<Category> getAllCategory (){
		List<Category> categories = new ArrayList<>();
		
		try {
			
			query = "select * from catagory";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Category category = new Category();
				category.setCategory_id(rs.getInt("catagory_id"));
				category.setCategory_name(rs.getString("catagory_name"));
				                                          
				categories.add(category);	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return categories;
	}
}
