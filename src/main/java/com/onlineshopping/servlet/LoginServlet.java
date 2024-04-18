package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.onlineshopping.connection.DBCon;
import com.onlineshopping.dbobject.OrderDbObject;
import com.onlineshopping.dbobject.ProductDbObject;
import com.onlineshopping.dbobject.UserDbObject;
import com.onlineshopping.model.*;

/**
 * Servlet implementation class LoginServlet
 */
// @WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out =  response.getWriter()){
			User user;
			
			String username=request.getParameter("login-username");
			String password=request.getParameter("login-password");
			
			if (username.equals("admin") && password.equals("admin")) {
				user = new User();
				user.setUsername(username);
				user.setCustomer_password(password);
				request.getSession().setAttribute("auth", user);
				
				response.sendRedirect("products.jsp");
			}
			
			UserDbObject userdb=new UserDbObject(DBCon.getConnection());
			user=userdb.userLogin(username, password);
			if (user!=null) {
				
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			}else {
				out.print("Incorrect credentials");
			}
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
