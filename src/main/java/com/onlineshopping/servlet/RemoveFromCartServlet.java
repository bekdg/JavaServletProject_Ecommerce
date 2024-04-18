package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.onlineshopping.model.Cart;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
//@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
				PrintWriter out = response.getWriter();
				String id = request.getParameter("id");
				out.println("Item remove servlet!");
				
				if(id!=null) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c: cart_list) {
							if(c.getId() == Integer.parseInt(id)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
						response.sendRedirect("Cart.jsp");
					}
				}else {
					response.sendRedirect("Cart.jsp");
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
