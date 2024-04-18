
<%@page import="java.util.*"%>
<%@page import="com.onlineshopping.connection.DBCon"%>
<%@page import="com.onlineshopping.dbobject.*"%>
<%@page import="com.onlineshopping.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	if (auth.getUsername().equals("admin")) {
		response.sendRedirect("products.jsp");
	}
	request.setAttribute("auth", auth);

}
List<Product> searchResult = (List<Product>) request.getSession().getAttribute("searchResult");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<%if (!searchResult.isEmpty()) {%>
	<div class="container">
		<div class="title text-center py-2">
			<h4 class="text-uppercase my-5">Search Results</h4>
		</div>
		<%for (Product p: searchResult){%>
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
					<a class="btn btn-secondary" style="color: white" href="add-to-cart?id=<%=p.getId()%>">Add to cart</a>
					<a class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy now</a>
				</div>
			</div>
		</div>
		

		<%}
		
}else {%>
		<div class="title text-center py-2">
			<h4 class="text-lowercase my-5">Sorry, we don't have such product!</h4>
		</div>
<%}
		%>
		
	</div>

<%@include file="Include/footer.jsp"%>
<%@include file="Include/foot.jsp"%>
</body>
</html>