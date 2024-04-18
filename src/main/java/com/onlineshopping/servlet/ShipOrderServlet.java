package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.onlineshopping.connection.DBCon;
import com.onlineshopping.dbobject.OrderDbObject;

/**
 * Servlet implementation class ShipOrderServlet
 */
public class ShipOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response .getWriter();
		int order_id = Integer.parseInt(request.getParameter("id"));
		out.println("Order id " + order_id);
		
		try {
			
			OrderDbObject orderDb = new OrderDbObject(DBCon.getConnection());
			boolean success = orderDb.shipOrder(order_id);
			if (success) {
				
				response.sendRedirect("manage_orders.jsp");
			}else {
				out.println("Failed to ship");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
