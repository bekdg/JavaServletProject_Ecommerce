
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.onlineshopping.model.*"%>
<%@page import="com.onlineshopping.dbobject.*"%>
<%@page import="com.onlineshopping.connection.*"%>
<%@page import="java.util.*"%>

<%    
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null && auth.getUsername().equals("admin")) {
	request.setAttribute("auth", auth);
}else {
	response.sendRedirect("login.jsp");
}

CategoryDbObject categoryDb = new CategoryDbObject(DBCon.getConnection());
List<Category> categories = categoryDb.getAllCategory();


%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Post Product Form</title>
<!-- Bootstrap CSS -->
<%@include file="Include/header.jsp"%>
</head>
<body>
<%@include file="Include/navbar.jsp"%>
	<div class="container mt-5 w-100">
		<h2 class="mb-4">Post Product</h2>
		<form action="post-product" method="post">
			<div class="form-group">
				<label for="productName">Product Name</label> <input type="text"
					class="form-control" id="productName" name="productName" required>
			</div>
			<div class="form-group">
				<label for="price">Price</label> <input type="number"
					class="form-control" id="price" name="price" required>
			</div>
			<div class="form-group">
				<label for="description">Description</label>
				<textarea class="form-control" id="description" name="description"
					rows="3" required></textarea>
			</div>
			<div class="form-group">
				<label for="category">Category</label> <select class="form-control"
					id="category" name="category" required>
					<%
					for (Category c: categories){%>
						<option value="<%=c.getCategory_id()%>"><%=c.getCategory_name()%></option>
					<%}
					%>
					<!-- Add more options as needed -->
				</select>
			</div>
			<div class="form-group">
				<label for="image">Image</label> <input type="file"
					class="form-control-file" id="image" name="image" accept="image/*"
					required>
			</div>
			<div class="form-group">
				<label for="quantity">Quantity in Stock</label> <input type="number"
					class="form-control" id="quantity" name="quantity" required>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<%@include file="Include/footer.jsp"%>
</body>
</html>
