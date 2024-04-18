<%@page import="java.util.*"%>
<%@page import="com.onlineshopping.dbobject.*"%>
<%@page import="com.onlineshopping.model.*"%>
<%@page import="com.onlineshopping.connection.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null && auth.getUsername().equals("admin")) {
	request.setAttribute("auth", auth);
} else {
	response.sendRedirect("login.jsp");
}
List<Order> orders = new ArrayList<>();
OrderDbObject orderDb = new OrderDbObject(DBCon.getConnection());
orders = orderDb.getAllOrders();
request.getSession().setAttribute("orders", orders);

List<Product> products = new ArrayList<>();
ProductDbObject productDb = new ProductDbObject(DBCon.getConnection());
products = productDb.getAllProducts();
request.getSession().setAttribute("products", products);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Products</title>
<%@include file="Include/header.jsp"%>
</head>
<body>
	<%@include file="Include/navbar.jsp"%>

	<div class="container">
		<div class="title text-center">
			<h1 class="text-uppercase my-5">List of All Products</h1>
		</div>
		<%for (Product p: products){%>
			<div class="card py-10" >
			<div class="row">
				<div class="col-md-4">
					<img class="img-fluid rounded-start"
						src="product image/<%=p.getImage_url()%>" alt="">
				</div>
				<div class="card-body">
					<h3 class="card-title"><%=p.getName() %></h3>
					<h6 class="price">Price: <%=p.getPrice() %> Birr</h6>
					<p class="card-text"><%=p.getDescription() %></p>
					<a class="btn btn-danger" href="delete-product?id=<%=p.getId()%>">Delete Product</a>
				</div>
			</div>
		</div>
		<%}
		
		
		%>
		
	</div>

	<%@include file="Include/footer.jsp"%>
</body>
</html>