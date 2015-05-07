<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/shopcart.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/shopcart/shopcart.js"></script> 
</head>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Shopping Cart</div>
			<div class="column4">&nbsp;</div>
			<div class="column3">Total</div>
			<div class="column2">Quantity</div>
			<div class="column1">Price</div>
			<div class="underline"></div>
			<div id="shopcart" class="productList">
				
			</div>
			<div id="checkout-area" class="list-bottom">
				<div class="button"><span onclick="checkout();" class="span-click-box">Check out</span></div>
				<div id="subtotal" class="text2"></div>
				<div id="subtotal-label" class="text1"></div>
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
	<div class="operate">
		<div id="message_{{= product_id}}" class="message"></div>
		<div class="op1"><span onclick="add2wishlist({{= product_id}});" class="span-click-box">Add to Wishlist</span></div>
		<div class="op2"><a href="javascript:void(0)" onclick="del({{= product_id}});">Delete item</a></div>
		<div class="op3"><a href="/Easybuy/message/new?touser={{= user_name_seller}}">Contact Seller</a></div>
	</div>
	<div class="column0"><font>{{= product_name}}</font></div>
	<div id="total" class="data-column style2">$ {{= toDecimal2(quantity * price)}}</div>
	<div class="data-column"><input name="quantity" onblur="calculateandsave(event,{{= product_id}});" id="quantity" type="text" class="input-text" value="{{= quantity}}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" /></div>
	<div class="data-column style1">$ {{= price}}</div>
	<input type="hidden" id="old" value="{{= quantity}}"/>
	<input type="hidden" id="price" value="{{= price}}"/>
</div>
{{/each}}
</script>
</div>
</body>
</html>