<%@page import="java.util.*"%>
<%@page import="com.onlineshopping.connection.*"%>
<%@page import="com.onlineshopping.dbobject.*"%>
<%@page import="com.onlineshopping.model.*"%>
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
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Orders</title>
<%@include file="Include/header.jsp"%>
</head>
<body>
	<%@include file="Include/navbar.jsp"%>

	<div class="container">
		<div class="title text-center">
			<h1 class="text-uppercase my-5">Manage Orders</h1>
		</div>
		<div class="card-header my-3">List of Orders</div>
		<table class="table table-light">
			<thead class="table table-light">
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Product Name</th>
					<th scope="col">Ordered by (username)</th>
					<th scope="col">Quantity</th>
					<th scope="col">Total Price</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (orders != null) {
					for (Order o : orders) {
				%>
				<tr>
					<td><%=o.getDate()%></td>
					<td><%=o.getName()%></td>
					<td><%=o.getUsername()%></td>
					<td><%=o.getQuantity()%></td>
					<td><%=o.getPrice()%></td>
					
					<%if (o.getStatus().equals("pending")) {%>
					<td>
					
					<a class="btn btn-outline-primary"
						href="ship-order?id=<%=o.getOrderId()%>">Ship</a></td>
						
						<%}else {%>
							<td><a class="btn btn-secondary" style="color: white">Shipped</a></td>
						<%}
						%>
				</tr>
				<%
				}
				}
				%>

			</tbody>
		</table>
	</div>


	<%@include file="Include/footer.jsp"%>
</body>
</html>