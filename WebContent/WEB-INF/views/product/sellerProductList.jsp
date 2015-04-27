<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/product.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/product/sellerProductList.js"></script> 
</head>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">My products</div>
			<div class="right"><a href="/Easybuy/product/new">Add new product</a></div>
			<div class="underline"></div>
			<div id="productList" class="productList">
				
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
	<div class="column1"><font>{{= product_name}}</font></div>
	<div class="column2"><font>$ {{= price}}</font></div>
	<div class="operate2"><a href="javascript:void(0)" onclick="del({{= product_id}});">Delete product</a></div>
	<div class="operate1"><a href="/Easybuy/product/edit?product_id={{= product_id}}">Update product info</a></div>
	
</div>
{{/each}}
</script>
</div>
</body>
</html>