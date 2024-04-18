<%@page import="java.util.*"%>
<%@page import="com.onlineshopping.connection.DBCon"%>
<%@page import="com.onlineshopping.dbobject.*"%>
<%@page import="com.onlineshopping.model.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	if (auth.getUsername().equals("admin")) {
		response.sendRedirect("products.jsp");
	}
	request.setAttribute("auth", auth);

}
ProductDbObject pd = new ProductDbObject(DBCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cart_list != null) {

	request.setAttribute("cart_list", cart_list);
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Et-Shop</title>
<%@include file="Include/header.jsp"%>
</head>
<body>
	<%@include file="Include/navbar.jsp"%>
	<div class="container py-3">
	<form action="search-products" method="get" class="d-flex">
		<input class="form-control me-2 px-2" type="search" name="searchQuery" placeholder="Search for Products..."
			aria-label="Search">
		<button class="btn btn-outline-success px-3" type="submit">Search</button>
	</form>
</div>

	<div class="container">

		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>

			<div class="col-md-3.5 mx-2 my-3">
				<div class="card" style="width: 16.4rem;">
					<img src="product image/<%=p.getImage_url()%>" class="card-img-top"
						alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName()%></h5>
						<h6 class="price">
							Price:
							<%=p.getPrice()%>
							Birr
						</h6>
						<h6 class="category">
							Category:
							<%=p.getCategory()%></h6>
						<p class="card-text"><%=p.getDescription()%></p>
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-secondary"
								style="color: white">Add to cart</a> <a
								href="order-now?quantity=1&id=<%=p.getId()%>"
								class="btn btn-primary">Buy now</a>
						</div>
					</div>
				</div>

			</div>

			<%
			}
			}
			%>

		</div>
	</div>

	<%@include file="Include/footer.jsp"%>
	<%@include file="Include/foot.jsp"%>
</body>
</html>
