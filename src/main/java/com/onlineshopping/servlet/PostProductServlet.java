package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.onlineshopping.connection.DBCon;
import com.onlineshopping.dbobject.ProductDbObject;
import com.onlineshopping.model.Product;

/**
 * Servlet implementation class PostProductServlet
 */
public class PostProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("product_post.jsp");
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		PrintWriter out = response.getWriter();
		
		Product product = new Product();
		
		
		
		String product_name = request.getParameter("productName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		String image = request.getParameter("image");
		String quantity = request.getParameter("quantity");
		
		product.setName(product_name);
		product.setPrice(Float.parseFloat(price));
		product.setDescription(description);
		product.setCategory(category);
		product.setImage_url(image);
		product.setQuantity(Integer.parseInt(quantity));
		
		ProductDbObject productDb = new ProductDbObject(DBCon.getConnection());
		boolean success = productDb.postProduct(product);
		if (success) {
			response.sendRedirect("products.jsp");
		}else {
			out.println("Failed to post");
		}
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
