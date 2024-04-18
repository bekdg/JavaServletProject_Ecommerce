package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.onlineshopping.connection.DBCon;
import com.onlineshopping.dbobject.ProductDbObject;
import com.onlineshopping.model.Product;

/**
 * Servlet implementation class SearchProductsServlet
 */
public class SearchProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("Search results!");
		String searchQuery = request.getParameter("searchQuery");
		try {
		ProductDbObject productDb = new ProductDbObject(DBCon.getConnection()); 
		
		List<Product> searchResult = productDb.searchProduct(searchQuery);
		request.getSession().setAttribute("searchResult", searchResult);
		response.sendRedirect("search_result.jsp");
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
