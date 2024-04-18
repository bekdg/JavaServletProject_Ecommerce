<%@page import="java.util.*"%>
<%@page import="com.onlineshopping.dbobject.*"%>
<%@page import="com.onlineshopping.connection.DBCon"%>
<%@page import="com.onlineshopping.model.*"%>
<%@page import="com.onlineshopping.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
 if (auth != null) {
	if (auth.getUsername().equals("admin")){
		response.sendRedirect("login.jsp");
	}
} 
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDbObject pdObj = new ProductDbObject(DBCon.getConnection());
	cartProduct = pdObj.getCartProducts(cart_list);
	double total = pdObj.getTotalCartPrice(cart_list);

	request.setAttribute("cart_list", cart_list);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Cart</title>
<%@include file="Include/header.jsp"%>
</head>
<body>
	<%@include file="Include/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Total Price: ${(total>0)?total: 0} Birr</h3>
			<a class="mx-3 btn btn-primary" href="cart-check-out">Check Out!</a>
		</div>

		<table class="table table-loght">
			<thead>

				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%=c.getPrice()%> Birr</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50" value="<%=c.getQuantity()%>" readonly>
								 <a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i> </a>
								<button type="submit" class="btn btn-primary btn-sm">Buy</button>
							</div>

						</form>

					</td>
					<td><a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%=c.getId()%>">Remove</a></td>

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
