<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.easybuy.order.domain.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/shopcart.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/shopcart/placeOrder.js"></script>  
</head>
<%
	Order order = (Order) request.getAttribute("order");
	String message = (String) request.getAttribute("message");
	if(message ==null){
		message = "";
	}
	if(order == null){
		order = new Order();
	}
%>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Checkout</div>
			<div id="message" class="ordermessage"><%= message%></div>
			<div class="underline"></div>
			<form id="orderForm" name="orderForm" method="post" action="/Easybuy/shopcart/placeorder">
				<div class="form-area-1">
					<label>Shipping Address</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Full name</div>
							<div class="input-box"><input name="full_name" id="full_name" type="text" value="<%= order.getFull_name() == null ? "" : order.getFull_name()%>" class="input-text"/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 1</div>
							<div class="input-box"><input name="address_1" id="address_1" type="text" value="<%= order.getAddress_1() == null ? "" : order.getAddress_1() %>" class="input-text" id="inputSuccess"/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 2</div>
							<div class="input-box"><input name="address_2" id="address_2" type="text" value="<%= order.getAddress_2() == null ? "" : order.getAddress_2() %>" class="input-text"/>&nbsp;</div>
						</div>
						<div class="input-row">
							<div class="label">Phone</div>
							<div class="input-box"><input name="phone" id="phone" type="text" value="<%= order.getPhone() == null ? "" : order.getPhone() %>" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  class="input-text"/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-2">
					<label>Shipping Option</label>
					<div class="radio-zone">
						<% if(order.getShipping_options() ==null || order.getShipping_options().equals("3")){ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options"> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options"> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="3" name="options"> free shipping ( deliver date not guaranteed )</div>
						<%}
						else if(order.getShipping_options().equals("1")){
						%>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="1" name="options"> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options"> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();"  value="3" name="options"> free shipping ( deliver date not guaranteed )</div>
						<%} else{ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options"> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="2"  name="options"> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="3" name="options"> free shipping ( deliver date not guaranteed )</div>
						<%} %>
					</div>
				</div>
				<div class="form-area-3">
					<label>Payment</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Card number</div>
							<div class="input-box"><input name="paypal_account" id="paypal_account" type="text" value="<%= order.getPaypal_account() == null ? "" : order.getPaypal_account() %>" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" class="input-text"/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Passcode</div>
							<div class="input-box"><input name="passcode" id="passcode" type="password" value="<%= order.getPasscode() == null ? "" : order.getPasscode() %>" class="input-text" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" id="inputSuccess"/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-4">
					<label>Items</label>
					<div class="label2">Quantity</div>
					<div class="label1">Price</div>
					<div id="itemList" class="itemList">
						
					</div>
				</div>
				<div id="checkout-area" class="order-bottom">
					<input type="hidden" name="price" id="price" value="" />
					<input type="hidden" name="tax" id="tax_input" value="" />
					<div class="text">Subtotal:</div><div id="subtotal" class="text1"></div>
					<div class="text">Tax:</div><div class="text1" id="tax"></div>
					<div class="text">Shipping cost:</div><div class="text1" id="shippingCost"></div>
					<div class="text2">Total:</div><div id="total" class="text3"></div>
					<div class="button"><span onclick="document.orderForm.submit();" class="span-click-box">place my order</span></div>
					
				</div>
			</form>
		</div>
    </div>
    
<div id="templates" style="display: none;">
<script x-id="list" type="text/x-template">
{{each list}}
<div id="{{= product_id}}" class="data-row">
	<img src="/Easybuy/{{= image}}" />
	<div class="column0"><font>{{= product_name}}</font></div>
	<div class="column1">{{= quantity}}</div>
	<div class="column2">$ {{= price}}</div>
</div>
{{/each}}
</script>
</div>
</body>
</html>