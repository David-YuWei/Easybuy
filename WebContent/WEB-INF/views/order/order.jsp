<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.easybuy.order.domain.*, java.util.*,java.text.*,com.easybuy.user.domain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/order.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/order/order.js"></script>  
</head>
<%
	Order order = (Order) request.getAttribute("order");
	User user = (User) session.getAttribute("user");
	String type = (String) request.getAttribute("operateType");
	String message = (String) request.getAttribute("message");
	DecimalFormat df = new DecimalFormat("#.00");
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
			<div class="title">Order #<%= order.getOrder_id() %></div>
			<div id="message" class="ordermessage"><%= message%></div>
			<div class="underline"></div>
			<% if(user instanceof Buyer){ %>
			<% if(type.equals("edit")){ %>
				<form id="orderForm" name="orderForm" method="post" action="/Easybuy/order/<%= order.getOrder_id() %>/updateInfo">
				<div class="form-area-0">
					<label>Order status</label>
					<div class="status-text">
						<% if(order.getStatus().equals("1")){ %>
							<input type="radio" value="1" name="order-status" checked="checked" disabled> ordered
							<input type="radio" value="2"  name="order-status" disabled> partially shipped
							<input type="radio" value="3"  name="order-status" disabled> delivered
						<%}else if(order.getStatus().equals("2")){ %>
							<input type="radio" value="1" name="order-status" disabled> ordered
							<input type="radio" value="2"  name="order-status" checked="checked" disabled> partially shipped
							<input type="radio" value="3"  name="order-status" disabled> delivered
						<%}else{ %>
							<input type="radio" value="1" name="order-status" disabled> ordered
							<input type="radio" value="2"  name="order-status"  disabled> partially shipped
							<input type="radio" value="3"  name="order-status" checked="checked" disabled> delivered
						<%} %>
					</div>
				</div>
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
							<div class="input-box"><input name="phone" id="phone" type="text" value="<%= order.getPhone() == null ? "" : order.getPhone() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
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
							<div class="input-box"><input name="paypal_account" id="paypal_account" type="text" value="<%= order.getPaypal_account() == null ? "" : order.getPaypal_account() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Passcode</div>
							<div class="input-box"><input name="passcode" id="passcode" type="text" value="<%= order.getPasscode() == null ? "" : order.getPasscode() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-4">
					<label>Items</label>
					<div class="label2">Quantity</div>
					<div class="label1">Price</div>
					<div id="itemList" class="item-list">
						<% for(OrderItem item: order.getItems()){ %>
							<div id="<%= item.getProduct_id() %>" class="data-row">
								<input type="hidden" id="price" value="<%= df.format(item.getPrice()) %>"/>
								<img src="/Easybuy/<%= item.getImage() %>" />
								<div class="column0"><font><%= item.getProduct_name() %></font></div>
								<div class="column2 w2"><input name="quantity_<%= item.getProduct_id() %>" onblur="updateQuantity(event);" id="quantity" type="text" class="input-text" value="<%= item.getQuantity() %>" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" /></div>
								<div class="column1 w1">$ <%= df.format(item.getPrice()) %></div>
							</div>
						<%} %>
					</div>
				</div>
				<div id="checkout-area" class="order-bottom">
					<div class="text">Subtotal:</div><div id="subtotal" class="text1"><%= df.format(order.getPrice()) %></div>
					<div class="text">Tax:</div><div class="text1" id="tax"><%= df.format(order.getTax()) %></div>
					<div class="text">Shipping cost:</div><div class="text1" id="shippingCost"><%= df.format(order.getShippingCost()) %></div>
					<div class="text2">Total:</div><div id="total" class="text3">$ <%= df.format(order.getPrice()+order.getTax()+order.getShippingCost()) %></div>
					<div class="button"><span onclick="document.orderForm.submit();" class="span-click-box">update my order</span></div>
					
				</div>
			</form>
			<%}else{ %>
				<div class="form-area-0">
					<label>Order status</label>
					<div class="status-text">
						<% if(order.getStatus().equals("1")){ %>
							ordered
						<%}else if(order.getStatus().equals("2")){ %>
							<font style="color:blue;">partially shipped</font>
						<%}else{ %>
							<font style="color:green;">delivered</font>
						<%} %>
					</div>
				</div>
				<div class="form-area-1">
					<label>Shipping Address</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Full name</div>
							<div class="input-box"><input name="full_name" id="full_name" type="text" value="<%= order.getFull_name() == null ? "" : order.getFull_name()%>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 1</div>
							<div class="input-box"><input name="address_1" id="address_1" type="text" value="<%= order.getAddress_1() == null ? "" : order.getAddress_1() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 2</div>
							<div class="input-box"><input name="address_2" id="address_2" type="text" value="<%= order.getAddress_2() == null ? "" : order.getAddress_2() %>" class="input-text" disabled/>&nbsp;</div>
						</div>
						<div class="input-row">
							<div class="label">Phone</div>
							<div class="input-box"><input name="phone" id="phone" type="text" value="<%= order.getPhone() == null ? "" : order.getPhone() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-2">
					<label>Shipping Option</label>
					<div class="radio-zone">
						<% if(order.getShipping_options() ==null || order.getShipping_options().equals("3")){ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%}
						else if(order.getShipping_options().equals("1")){
						%>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();"  value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%} else{ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%} %>
					</div>
				</div>
				<div class="form-area-3">
					<label>Payment</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Card number</div>
							<div class="input-box"><input name="paypal_account" id="paypal_account" type="text" value="<%= order.getPaypal_account() == null ? "" : order.getPaypal_account() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Passcode</div>
							<div class="input-box"><input name="passcode" id="passcode" type="text" value="<%= order.getPasscode() == null ? "" : order.getPasscode() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-4">
					<label>Items</label>
					<div class="label2">Quantity</div>
					<div class="label1">Price</div>
					<div id="itemList" class="item-list">
						<% for(OrderItem item: order.getItems()){ %>
							<div id="<%= item.getProduct_id() %>" class="data-row">
								<img src="/Easybuy/<%= item.getImage() %>" />
								<div class="column0"><font><%= item.getProduct_name() %></font></div>
								<div class="column2 w2"><%= item.getQuantity() %></div>
								<div class="column1 w1">$ <%= df.format(item.getPrice()) %></div>
							</div>
						<%} %>
					</div>
				</div>
				<div id="checkout-area" class="order-bottom">
					<div class="text">Subtotal:</div><div id="subtotal" class="text1"><%= df.format(order.getPrice()) %></div>
					<div class="text">Tax:</div><div class="text1" id="tax"><%= df.format(order.getTax()) %></div>
					<div class="text">Shipping cost:</div><div class="text1" id="shippingCost"><%= df.format(order.getShippingCost()) %></div>
					<div class="text2">Total:</div><div id="total" class="text3">$ <%= df.format(order.getPrice()+order.getTax()+order.getShippingCost()) %></div>
					<div class="button"></div>
				</div>
			<%} %>
			<%}else if(user instanceof Seller){ %>
				<% if(type.equals("edit")){ %>
				<form id="orderForm" name="orderForm" method="post" action="/Easybuy/order/<%= order.getOrder_id() %>/updateInfo">
				<div class="form-area-0">
					<label>Order status</label>
					<div class="status-text">
						<% if(order.getStatus().equals("1")){ %>
							<input type="radio" value="1" name="order-status" checked="checked" disabled> ordered
							<input type="radio" value="2"  name="order-status" disabled> partially shipped
							<input type="radio" value="3"  name="order-status" disabled> delivered
						<%}else if(order.getStatus().equals("2")){ %>
							<input type="radio" value="1" name="order-status" disabled> ordered
							<input type="radio" value="2"  name="order-status" checked="checked" disabled> partially shipped
							<input type="radio" value="3"  name="order-status" disabled> delivered
						<%}else{ %>
							<input type="radio" value="1" name="order-status" disabled> ordered
							<input type="radio" value="2"  name="order-status"  disabled> partially shipped
							<input type="radio" value="3"  name="order-status" checked="checked" disabled> delivered
						<%} %>
					</div>
				</div>
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
							<div class="input-box"><input name="phone" id="phone" type="text" value="<%= order.getPhone() == null ? "" : order.getPhone() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
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
							<div class="input-box"><input name="paypal_account" id="paypal_account" type="text" value="<%= order.getPaypal_account() == null ? "" : order.getPaypal_account() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Passcode</div>
							<div class="input-box"><input name="passcode" id="passcode" type="text" value="<%= order.getPasscode() == null ? "" : order.getPasscode() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-4">
					<label>Items</label>
					<div class="label2">Quantity</div>
					<div class="label1">Price</div>
					<div id="itemList" class="item-list">
						<% for(OrderItem item: order.getItems()){ %>
							<div id="<%= item.getProduct_id() %>" class="data-row">
								<input type="hidden" id="price" value="<%= df.format(item.getPrice()) %>"/>
								<img src="/Easybuy/<%= item.getImage() %>" />
								<div class="column0"><font><%= item.getProduct_name() %></font></div>
								<div class="column2 w2"><input name="quantity_<%= item.getProduct_id() %>" onblur="updateQuantity(event);" id="quantity" type="text" class="input-text" value="<%= item.getQuantity() %>" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" /></div>
								<div class="column1 w1">$ <%= df.format(item.getPrice()) %></div>
							</div>
						<%} %>
					</div>
				</div>
				<div id="checkout-area" class="order-bottom">
					<div class="text">Subtotal:</div><div id="subtotal" class="text1"><%= df.format(order.getPrice()) %></div>
					<div class="text">Tax:</div><div class="text1" id="tax"><%= df.format(order.getTax()) %></div>
					<div class="text">Shipping cost:</div><div class="text1" id="shippingCost"><%= df.format(order.getShippingCost()) %></div>
					<div class="text2">Total:</div><div id="total" class="text3">$ <%= df.format(order.getPrice()+order.getTax()+order.getShippingCost()) %></div>
					<div class="button"><span onclick="document.orderForm.submit();" class="span-click-box">update my order</span></div>
					
				</div>
			</form>
			<%}else{ %>
				<div class="form-area-0">
					<label>Order status</label>
					<div class="status-text">
						<% if(order.getStatus().equals("1")){ %>
							ordered
						<%}else if(order.getStatus().equals("2")){ %>
							<font style="color:blue;">partially shipped</font>
						<%}else{ %>
							<font style="color:green;">delivered</font>
						<%} %>
					</div>
				</div>
				<div class="form-area-1">
					<label>Shipping Address</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Full name</div>
							<div class="input-box"><input name="full_name" id="full_name" type="text" value="<%= order.getFull_name() == null ? "" : order.getFull_name()%>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 1</div>
							<div class="input-box"><input name="address_1" id="address_1" type="text" value="<%= order.getAddress_1() == null ? "" : order.getAddress_1() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 2</div>
							<div class="input-box"><input name="address_2" id="address_2" type="text" value="<%= order.getAddress_2() == null ? "" : order.getAddress_2() %>" class="input-text" disabled/>&nbsp;</div>
						</div>
						<div class="input-row">
							<div class="label">Phone</div>
							<div class="input-box"><input name="phone" id="phone" type="text" value="<%= order.getPhone() == null ? "" : order.getPhone() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-2">
					<label>Shipping Option</label>
					<div class="radio-zone">
						<% if(order.getShipping_options() ==null || order.getShipping_options().equals("3")){ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%}
						else if(order.getShipping_options().equals("1")){
						%>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();"  value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%} else{ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%} %>
					</div>
				</div>
				<div class="form-area-3">
					<label>Payment</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Card number</div>
							<div class="input-box"><input name="paypal_account" id="paypal_account" type="text" value="<%= order.getPaypal_account() == null ? "" : order.getPaypal_account() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Passcode</div>
							<div class="input-box"><input name="passcode" id="passcode" type="text" value="<%= order.getPasscode() == null ? "" : order.getPasscode() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-4">
					<label>Items</label>
					<div class="label2">Quantity</div>
					<div class="label1">Price</div>
					<div id="itemList" class="item-list">
						<% for(OrderItem item: order.getItems()){ %>
							<div id="<%= item.getProduct_id() %>" class="data-row">
								<img src="/Easybuy/<%= item.getImage() %>" />
								<div class="column0"><font><%= item.getProduct_name() %></font></div>
								<div class="column2 w2"><%= item.getQuantity() %></div>
								<div class="column1 w1">$ <%= df.format(item.getPrice()) %></div>
							</div>
						<%} %>
					</div>
				</div>
				<!--  
				<div id="checkout-area" class="order-bottom">
					<div class="text">Subtotal:</div><div id="subtotal" class="text1"><%= df.format(order.getPrice()) %></div>
					<div class="text">Tax:</div><div class="text1" id="tax"><%= df.format(order.getTax()) %></div>
					<div class="text">Shipping cost:</div><div class="text1" id="shippingCost"><%= df.format(order.getShippingCost()) %></div>
					<div class="text2">Total:</div><div id="total" class="text3"><%= df.format(order.getPrice()+order.getTax()+order.getShippingCost()) %></div>
					<div class="button"></div>
				</div>
				-->
			<%} %>
			<%}else{ %>
				<div class="form-area-0">
					<label>Order status</label>
					<div class="status-text">
						<% if(order.getStatus().equals("1")){ %>
							ordered
						<%}else if(order.getStatus().equals("2")){ %>
							<font style="color:blue;">partially shipped</font>
						<%}else{ %>
							<font style="color:green;">delivered</font>
						<%} %>
					</div>
				</div>
				<div class="form-area-1">
					<label>Shipping Address</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Full name</div>
							<div class="input-box"><input name="full_name" id="full_name" type="text" value="<%= order.getFull_name() == null ? "" : order.getFull_name()%>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 1</div>
							<div class="input-box"><input name="address_1" id="address_1" type="text" value="<%= order.getAddress_1() == null ? "" : order.getAddress_1() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Address line 2</div>
							<div class="input-box"><input name="address_2" id="address_2" type="text" value="<%= order.getAddress_2() == null ? "" : order.getAddress_2() %>" class="input-text" disabled/>&nbsp;</div>
						</div>
						<div class="input-row">
							<div class="label">Phone</div>
							<div class="input-box"><input name="phone" id="phone" type="text" value="<%= order.getPhone() == null ? "" : order.getPhone() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-2">
					<label>Shipping Option</label>
					<div class="radio-zone">
						<% if(order.getShipping_options() ==null || order.getShipping_options().equals("3")){ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%}
						else if(order.getShipping_options().equals("1")){
						%>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();"  value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%} else{ %>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="1" name="options" disabled> deliver within 2 - 4 days ( cost $3 ) </div>
						<div class="radio-box"><input type="radio" onChange="calculate();" checked value="2"  name="options" disabled> get it by today ( cost $10 )</div>
						<div class="radio-box"><input type="radio" onChange="calculate();" value="3" name="options" disabled> free shipping ( deliver date not guaranteed )</div>
						<%} %>
					</div>
				</div>
				<div class="form-area-3">
					<label>Payment</label>
					<div class="input-zone">
						<div class="input-row">
							<div class="label">Card number</div>
							<div class="input-box"><input name="paypal_account" id="paypal_account" type="text" value="<%= order.getPaypal_account() == null ? "" : order.getPaypal_account() %>" class="input-text" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
						<div class="input-row">
							<div class="label">Passcode</div>
							<div class="input-box"><input name="passcode" id="passcode" type="text" value="<%= order.getPasscode() == null ? "" : order.getPasscode() %>" class="input-text" id="inputSuccess" disabled/>&nbsp;<font color="red">*</font></div>
						</div>
					</div>
				</div>
				<div class="form-area-4">
					<label>Items</label>
					<div class="label2">Quantity</div>
					<div class="label1">Price</div>
					<div id="itemList" class="item-list">
						<% for(OrderItem item: order.getItems()){ %>
							<div id="<%= item.getProduct_id() %>" class="data-row">
								<img src="/Easybuy/<%= item.getImage() %>" />
								<div class="column0"><font><%= item.getProduct_name() %></font></div>
								<div class="column2 w2"><%= item.getQuantity() %></div>
								<div class="column1 w1">$ <%= df.format(item.getPrice()) %></div>
							</div>
						<%} %>
					</div>
				</div>
				<div id="checkout-area" class="order-bottom">
					<div class="text">Subtotal:</div><div id="subtotal" class="text1"><%= df.format(order.getPrice()) %></div>
					<div class="text">Tax:</div><div class="text1" id="tax"><%= df.format(order.getTax()) %></div>
					<div class="text">Shipping cost:</div><div class="text1" id="shippingCost"><%= df.format(order.getShippingCost()) %></div>
					<div class="text2">Total:</div><div id="total" class="text3">$ <%= df.format(order.getPrice()+order.getTax()+order.getShippingCost()) %></div>
					<div class="button"></div>
				</div>
			<%} %>
		</div>
    </div>
    
</body>
</html>