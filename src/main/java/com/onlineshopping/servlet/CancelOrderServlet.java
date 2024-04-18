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
 * Servlet implementation class CancelOrderServlet
 */
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			String order_id = request.getParameter("id");
			if (order_id != null) {
				OrderDbObject orderDb = new OrderDbObject(DBCon.getConnection());
				orderDb.cancelOrder(Integer.parseInt(order_id));
			}
			response.sendRedirect("order.jsp");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
