<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/product.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/wishlist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/wishlist/wishlist.js"></script> 
</head>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Wish list</div>
			<div class="underline"></div>
			<div id="wishlist" class="productList">
				
			</div>
			<div id="pagebar" class="pagebar">
			</div>
		</div>
    </div>
    
<div id="templates" style="display: none;">
<script x-id="pagebar" type="text/x-template">
<jsp:include page='../pagebar.jsp'></jsp:include>
</script>
<script x-id="list" type="text/x-template">
{{each list}}
<div id="{{= product_id}}" class="data-row">
	<img src="/Easybuy/{{= image}}" />
	<div class="info">
		<div class="area1"><font>{{= product_name}}</font></div>
		<div class="area2"><font>$ {{= price}}</font></div>
		<div class="area3"><font class="font1">{{= ranking}}</font>/5.0</div>
	</div>
	<div class="operate">
		<div id="message_{{= product_id}}" class="message"></div>
		<div class="op1"><span onclick="add2cart({{= product_id}});" class="span-click-box">Add to Cart</span></div>
		<div class="op2"><a href="javascript:void(0)" onclick="del({{= product_id}});">Delete item</a></div>
		<div class="op3"><a href="/Easybuy/message/new?touser={{= user_name}}">Contact Seller</a></div>
	</div>
</div>
{{/each}}
</script>
</div>
</body>
</html>