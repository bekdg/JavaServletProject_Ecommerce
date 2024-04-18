<%@page import="java.util.*"%>

<%@page import="com.onlineshopping.connection.DBCon"%>
<%@page import="com.onlineshopping.model.*"%>
<%@page import="com.onlineshopping.dbobject.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders=null;
if (auth != null) {
	if (auth.getUsername().equals("admin")){
		response.sendRedirect("login.jsp");
	}
	request.setAttribute("auth", auth);
	
	orders = new OrderDbObject (DBCon.getConnection()).userOrders(auth.getUsername());
	
} else {
	response.sendRedirect("login.jsp");
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Order</title>
<%@include file="Include/header.jsp"%>
</head>
<body>
	<%@include file="Include/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead class="table table-light">
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Product Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Status</th>
					<th scope="col">Action</th>
				</tr>
			</thead>
			<tbody>
			<%
			if (orders != null){
				for (Order o: orders) {%>
				<tr>
					<td><%=o.getDate() %></td>
					<td><%=o.getName() %></td>
					<td><%=o.getCategory() %></td>
					<td><%=o.getQuantity() %></td>
					<td><%=o.getPrice() %></td>
					<%if(o.getStatus().equals("shipped")){%>
						<td>Shipped</td>
						<td><a class="btn btn-outline-dark" href="cancel-order?id=<%=o.getOrderId()%>">Remove from List</a></td>
					<% }else {%>
						<td>Pending...</td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel</a></td>
						
					<%}%>
					
				</tr>
				<%}
			}
			
			%>
			
			</tbody>
		</table>
	</div>


	<%@include file="Include/footer.jsp"%>
</body>
</html>
