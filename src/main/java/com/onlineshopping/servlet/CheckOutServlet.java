package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.onlineshopping.connection.DBCon;
import com.onlineshopping.dbobject.OrderDbObject;
import com.onlineshopping.model.*;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter out = response.getWriter();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			 
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			
			if(cart_list != null && auth != null) {
				
				for (Cart c: cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUsername(auth.getUsername());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDbObject orderDb = new OrderDbObject(DBCon.getConnection());
					boolean result = orderDb.insertOrder(order);
					
					if (!result) break;
				}
				cart_list.clear();
				response.sendRedirect("order.jsp");
				

				
			}else {
				if (auth == null) {
					response.sendRedirect("login.jsp");
				}else {
					response.sendRedirect("Cart.jsp");
				}
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
