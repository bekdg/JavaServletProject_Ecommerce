package com.onlineshopping.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.onlineshopping.connection.DBCon;
import com.onlineshopping.dbobject.UserDbObject;
import com.onlineshopping.model.User;

/**
 * Servlet implementation class SignupUserServlet
 */
public class SignupUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("signup.jsp");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		User user = new User();
		
		PrintWriter out = response.getWriter();
		
		
		String username = request.getParameter("username");
		String first_name = request.getParameter("firstName");
		String last_name = request.getParameter("lastName");
		String email = request.getParameter("email");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String phone_number = request.getParameter("phone");
		String password1 = request.getParameter("password");
		String password2 = request.getParameter("confirmPassword");
		
		UserDbObject userDb = new UserDbObject(DBCon.getConnection());
		
		
		if (userDb.validUsername(username)) {
			if (password1.equals(password2)) {
				user.setUsername(username);
				user.setFirst_name(first_name);
				user.setLast_name(last_name);
				user.setEmail(email);
				user.setAge(Integer.parseInt(age));
				user.setAddress(address);
				user.setPhone_number(phone_number);
				user.setCustomer_password(password1);
				
				boolean success = userDb.registerUser(user);
				if (success) {
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				}
			}else {
				out.println(password1);
				out.println(password2);
				out.println("Password do not match");
			}
		}else {
			out.println("Username already exist");
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
