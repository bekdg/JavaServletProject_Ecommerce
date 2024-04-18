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
import com.onlineshopping.model.Cart;
import com.onlineshopping.model.Order;
import com.onlineshopping.model.User;

/**
 * Servlet implementation class OrderNowServlet
 */
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			PrintWriter out = response.getWriter();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();

			User auth = (User) request.getSession().getAttribute("auth");
			if (auth != null) {

				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				if (productQuantity <= 0) {
					productQuantity = 1;
				}

				Order orderModel = new Order();
				orderModel.setId(Integer.parseInt(productId));
				orderModel.setUsername(auth.getUsername());
				orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));

				OrderDbObject orderDb = new OrderDbObject(DBCon.getConnection());
				boolean result = orderDb.insertOrder(orderModel);

				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("order.jsp");

				} else {
					out.println("Order failed");
				}
			} else {
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
