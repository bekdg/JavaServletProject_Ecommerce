<%@page import="java.util.*"%>
<%@page import="com.onlineshopping.connection.DBCon"%>
<%@page import="com.onlineshopping.dbobject.*" %>
<%@page import="com.onlineshopping.model.*"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
ProductDbObject pd = new ProductDbObject(DBCon.getConnection());
List<Product> products = pd.getAllProducts();


ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

if (cart_list !=null){
	
	request.setAttribute("cart_list", cart_list); 
}
%>

<!DOCTYPE html>
<html>
<head>
<title>King.et Onlines_Shopping</title>
<%@include file="Include/header.jsp"%>
</head>
<body>
	<%@include file="Include/navbar.jsp"%>

	<div class="container">
		<div class="form my-4" >
			<input type="text"
				class="form-control form-input" placeholder="Search anything...">
			<a href="#" class="search-icon"> <i class="fa fa-search"></i>
			</a> 
		</div>
		
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<%
			if (!products.isEmpty()){
				for (Product p:products){ %>
				
				<div class="col-md-3 mx-2 my-3">
				<div class="card" style="width: 18rem;">
					<img src="product image/<%=p.getImage_url()%>" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: <%=p.getPrice() %> Birr</h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<p class="card-text"><%=p.getDescription() %></p>
						<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark">Add to cart</a> <a href="order-now?quantity=1&id=<%=p.getId()%>"
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
</body>
</html>
