<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.easybuy.user.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/order.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/order/orderList.js"></script> 
</head>
<% 
	User user = (User) session.getAttribute("user");
%>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Orders</div>
			<div class="underline"></div>
			<div id="orders" class="orderList">
				
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
{{each(n, order) list}}
<div id="order_{{= order.order_id}}" class="data-row">
	<div class="data-row-header">
		<div class="info1">{{= order.order_time}}</div>
		<div class="info2"><a href="/Easybuy/order/{{= order.order_id}}/view">Order # {{= order.order_id}}</a></div>
		<div class="info3">
			{{if order.status =='1'}}<font style="color:black;font-size:16px;">ordered</font>
			{{else order.status =='2'}}<font style="color:blue;">partially shipped</font>
			{{else order.status =='3'}}<font style="color:green;">delivered</font>
			{{/if}}
		</div>
<% if(user instanceof Buyer){ %>		
			<div class="info4"><a href="/Easybuy/order/{{= order.order_id}}/edit">Update order details</a>
		&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="delOrder({{= order.order_id}});">Delete order</a>
		</div>
	<% }
	else if(user instanceof Seller){
	%>	
		<div class="info4"><a href="/Easybuy/order/{{= order.order_id}}/edit">View order details</a>
		</div>
	<%}
	else{
	%>
		<div class="info5"><a href="javascript:void(0)" onclick="delOrder({{= order.order_id}});">Delete order</a>
		</div>
	<%}%>
		
	</div>
	
	<div class="item-list">
	{{each(x,item) order.items}}
		<div class="item" id="item_{{= item.product_id}}">
			<img src="/Easybuy/{{= item.image}}" />
			<div class="column0"><font>{{= item.product_name}}</font></div>
			<div class="column1">$ {{= item.price}}</div>
			<div class="column2">{{= item.quantity}}</div>
	<% if(user instanceof Buyer){ %>		
			<div class="operate1">
				<div id="message_{{= order.order_id}}_{{= item.product_id}}" class="message"></div>
				<div class="op1"><span onclick="add2cart({{= item.product_id}},{{= order.order_id}});" class="span-click-box">Buy it Again</span></div>
				<div class="op2"><a href="javascript:void(0)" onclick="delItem({{= item.product_id}},{{= order.order_id}});">Delete item</a></div>
				{{if item.status =='2'}}<div class="op3"><a href="/Easybuy/product/review/new?product_id={{= item.product_id}}">Review</a></div>
				{{else}}<div class="op3"><font style="color:gray;">Review</font></div>
				{{/if}}
				<div class="op4"><a href="/Easybuy/message/new?touser={{= item.user_name}}">Contact Seller</a></div>
			</div>
	<% }
	else if(user instanceof Seller){
	%>	
		<div class="operate2">
				<div id="message_{{= order.order_id}}_{{= item.product_id}}" class="message"></div>
				<div class="op1">
					{{if item.status =='1'}}<span onclick="shipItem({{= item.product_id}},{{= order.order_id}});" class="span-click-box">Ship this item</span>
					{{else item.status =='2'}}<font style="color:green;">shipped</font>
					{{/if}}
				</div>
				<div class="op4"><a href="/Easybuy/message/new?touser={{= order.user_name}}">Contact Buyer</a></div>
			</div>
	<%}
	else{
	%>
		<div class="operate3">
				<div class="op1"><a href="/Easybuy/message/new?touser={{= order.user_name}}">Contact Buyer</a></div>
				<div class="op2"><a href="/Easybuy/message/new?touser={{= item.user_name}}">Contact Seller</a></div>
		</div>
	<%}%>		
		</div>
	{{/each}}
	</div>
</div>
{{/each}}
</script>
</div>
</body>
</html>