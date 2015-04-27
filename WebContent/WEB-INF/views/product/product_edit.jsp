<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.easybuy.product.domain.Product, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/product.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/product/product_edit.js"></script>
</head>
<% 
	Product product = (Product) request.getAttribute("product");
	String message = (String)request.getAttribute("message");
	if(message ==null)
	{
		message = "";
	}
%>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Edit product</div>
			<div class="underline"></div>
			<div class="productForm">
				<form id="form" enctype="multipart/form-data" name="form" method="post" action="/Easybuy/product/update">
					<input type="hidden" name="product_id" value="<%= product.getProduct_id()%>" />
					<div class="input-row">
						<div class="label">product name</div>
						<div class="input-box"><input name="product_name" id="product_name" type="text" value="<%= product.getProduct_name()%>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div  class="label">price</div>
						<div class="input-box"><input name="price" id="price" type="text" value="<%= product.getPrice()%>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div  class="label">image</div>
						<div class="input-box"><input name="image" id="image" type="file" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div  class="label">&nbsp;</div>
						<div class="input-box"><img src="/Easybuy/<%= product.getImage() %>"/></div>
					</div>
					<div class="input-row">
						<div  class="label">brand name</div>
						<div class="input-box"><input name="brand_name" id="brand_name" type="text" value="<%= product.getBrand_name()%>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="textarea-row">
						<div  class="label">description</div>
						<div class="input-box"><textarea name="description" id="description" rows="5" class="input-textarea"><%= product.getDescription()%></textarea>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="save-box"><span class="span-click-box" onClick="document.form.submit();">Save</span></div>
					<div class="save-info"><%= message %></div>
				</form>
			</div>
		</div>
    </div>
</body>
</html>