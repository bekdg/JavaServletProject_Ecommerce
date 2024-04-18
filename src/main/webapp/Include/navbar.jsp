<%@page import="com.onlineshopping.model.*"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<h1>
			<a class="navbar-brand" href="index.jsp">Et-Shop </a>
		</h1>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="Cart.jsp">Cart
						<span class="badge badge-danger px-1">${cart_list.size()}</span>
				</a></li>
				<%
				if (auth != null) {

					if (auth.getUsername().equals("admin")) {
				%>
				<li class="nav-item"><a class="nav-link" href="product_post.jsp">Post Product</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="manage_orders.jsp">Manage Orders</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="log-out">Log
						out</a></li>
				<%
				}else {
				%>
				<li class="nav-item"><a class="nav-link" href="order.jsp">Orders</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="log-out">Log
						out</a></li>
				<%
				}
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a>
				</li>
				<%
				}
				%>



			</ul>
		</div>
	</div>
</nav>