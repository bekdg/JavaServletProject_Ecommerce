<%@page import="com.onlineshopping.model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    User auth= (User) request.getSession().getAttribute("auth");
    if (auth != null) {
		response.sendRedirect("index.jsp");
	}
    %>
<!DOCTYPE html>
<html>
<head>
<title>Online Shopping Login</title>
<%@include file ="Include/header.jsp" %>
</head>
<body>
	<div class="container w-50"> 
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">
				<div class="card-body">
					<form action="user-login" Method="post"> 
						<div class="form-group">
							<label>Username</label>
							<input type="text" class ="form-control" name="login-username" placeholder="Enter Your Username " required>
						</div>
						<div class="form-group">
							<label>Password</label>
							<input type="password" class ="form-control" name="login-password" placeholder="Enter Your Password  " required>
						</div>
						<div class="text-center">
							<button type="submit" class="btn btn-primary">Login</button>
						</div>
						<div class="Sign-up">
							<p>Don't have an account?<a class="nav-link" href="signup>.jsp">Sign Up</a></p>
						</div>
						<style>
							.Sign-up span{
								display:flex;
								align-items:center;	
							}
						</style>
					
					</form>
				</div>
			</div>
		</div>
	</div>
 <%@include file ="Include/footer.jsp" %>
</body>
</html> 