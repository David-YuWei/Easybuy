<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.easybuy.user.domain.*, com.easybuy.product.domain.Product, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
</head>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/product.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/product/product.js"></script>
</head>
<% 
	User user = (User) session.getAttribute("user");
	Product product = (Product) request.getAttribute("product");
	List<String> brands = (List<String>)request.getAttribute("brand_list");
%>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="searchBar">
			<div class="logo">
				<a href="/Easybuy/">
					<img alt="" src="/Easybuy/images/logo.png"  />
				</a>
			</div>
			<div class="searchArea">
				<form id="form" name="searchForm" method="post" action="/Easybuy/product/search">
					<select id="brand_name" name="brand_name" class="list-select">
					  	<option value="">All</option>
					<% Iterator it = brands.iterator();
				  	   String brand = "";
				  		while(it.hasNext()){
				  			brand = it.next().toString();
	  				%>
				  			<option value="<%=brand %>" ><%=brand %></option>
				  	<%
				  		}
				  	%>
					</select>
					<input id="content" name="content" type="text" class="input-text"/>
					<span class="searchButton" onclick="document.searchForm.submit();"></span>
				</form>
			</div>
		</div>
		<div class="content">
			<input type="hidden" id="product_id" value="<%= product.getProduct_id() %>" />
			<div class="underline"></div>
			<div class="product">
				<div class="info">
					<div class="img"><img src="/Easybuy/<%= product.getImage() %>" /></div>
					<div class="name"><%= product.getProduct_name() %></div>
					<div class="price"><font>$ <%= product.getPrice() %></font></div>
					<div class="score"><font><%= product.getRanking() %></font>/5</div>
					<div class="operate">
						<% if(user ==null || user instanceof Buyer){ %>
							<span id="add2cart" class="span-click-box" onClick="add2cart();">Add to Cart</span>
							<span id="add2wishlist" class="span-click-box" onClick="add2wishlist();">Add to Wishlist</span>
						<% }else{ %>
							<span id="add2cart" class="span-click-box gray-box">Add to Cart</span>
							<span id="add2wishlist" class="span-click-box gray-box">Add to Wishlist</span>
						<%} %>
					</div>
					<div id="message" class="message"></div>
				</div>
				<div class="description">
					<b>Product details</b>
					<div class="underline"></div>
					<div class="textArea"><font><%= product.getDescription() %></font></div>
				</div>
				<div class="reviews">
					<b>Reviews</b>
					<div class="underline"></div>
					<div id="reviewList" class="textArea">
						
					</div>
					<div id="pagebar" class="pagebar">
					</div>
				</div>
			</div>
		</div>
    </div>
    
<div id="templates" style="display: none;">
<script x-id="pagebar" type="text/x-template">
<jsp:include page='../pagebar.jsp'></jsp:include>
</script>
<script x-id="list" type="text/x-template">
<div class="data-head">
	<span class="item_column1">comment</span>
	<span class="item_column2">rating</span>
	<span class="item_column3">reviewer</span>
</div>
{{each list}}
<div id="{{= user_name}}" class="data-row">
	<div class="data_column1"><font>{{= review}}</font></div>
	<div class="data_column2"><font>{{= ranking}}</font></div>
	<div class="data_column3">
		<font>{{= user_name}}</font>
	</div>
</div>
{{/each}}
</script>
</div>
</body>
</html>