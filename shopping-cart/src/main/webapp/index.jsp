<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="cn.shoppingcart.connection.DbCon"%>
<%@page import="cn.shoppingcart.model.*"%>
<%@page import="cn.shoppingcart.dao.*"%>
<%@page import="java.util.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> list = pd.getAllProduct();

ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart-list");

if(cart_list != null){
	ProductDao pDao = new ProductDao(DbCon.getConnection());
	request.setAttribute("cart_list", cart_list);
}


%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to Shopping Cart</title>



<%@include file="includes/head.jsp"%>

</head>
<body>

	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
		<%
			if(!list.isEmpty()){
				
				//out.println(list);
				
				for(Product p:list){%>
				  <!--  <%out.print(p.getCategory()); %>-->
				  
					<div class="col-md-3 my-3">

					<div class="card w-100" style="width: 18rem;">
						<img src="product-images/<%=p.getImage() %>" class="card-img-top" alt="...">
						<div class="card-body">
							<h5 class="card-title"><%=p.getName()%></h5>
							<h6 class="price">Price: $<%=p.getPrice() %></h6>
							<h7 class="category">Category:<%=p.getCategory() %></h7>
							<div class="mt-3 d-flex justify-content-between">
								<a href="AddToCartServlet?id=<%=p.getId() %> " class="btn btn-dark">Add to Cart</a> 
								<a href="OrderNowServlet?quantity=1&id=<%=p.getId()%>"class="btn btn-primary">Buy Now</a>
							</div>

						</div>
					</div>

				</div>
				<% }
			}
		%>
			
		</div>

	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>