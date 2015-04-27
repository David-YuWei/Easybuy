<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/index.js"></script>
</head>
<body>
	<div class="container">
		<jsp:include page='header.jsp'>
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
					</select>
					<input id="content" name="content" type="text" class="input-text"/>
					<span class="searchButton" onclick="document.searchForm.submit();"></span>
				</form>
			</div>
		</div>
		<div class="content">
			<div class="section">Hot product</div>
			<div class="underline"></div>
			<div id="hotProduct" class="productArea"></div>
		</div>
		<div class="footer"></div>
    </div>
    
    
<div id="templates" style="display: none;">
<script x-id="brand_list" type="text/x-template">
{{each(i,v) brand_list}}
	<option value="{{= v}}">{{= v}}</option>
{{/each}}
</script>
<script x-id="list" type="text/x-template">
{{each list}}
<div id="p_{{= product_id}}" class="productBox">
	<div class="img">
		<a href="/Easybuy/product/{{= product_id}}/view">
			<img alt="" src="/Easybuy/{{= image}}" />
		</a>
	</div>
	<div class="name">{{= product_name}}</div>
	<div class="price"><font>$ {{= price}}</font></div>
	<div class="score"><font>{{= ranking}}</font>/5.0</div>
</div>
{{/each}}
</script>
</div>
</body>


</html>