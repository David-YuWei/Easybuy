<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
<script type="text/javascript" src="/Easybuy/js/product/searchProduct.js"></script>
</head>
<% 
	String brand_name = (String) request.getAttribute("brand_name");
	String content = (String) request.getAttribute("content");
	List<String> brands = (List<String>)request.getAttribute("brand_list");
	if(brand_name ==null){
		brand_name = "";
	}
	
	if(content == null){
		content = "";
	}
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
				<select id="brand_name" class="list-select">
				  	<option value="">All</option>
				  	<% Iterator it = brands.iterator();
				  	   String brand = "";
				  		while(it.hasNext()){
				  			brand = it.next().toString();
				  			if(brand.equals(brand_name)){
	  				%>
							  	<option value="<%=brand %>" selected><%=brand %></option>
				  	<%			
				  			}
				  			else{
				  	%>
				  				<option value="<%=brand %>" ><%=brand %></option>
				  	<%
				  			}
				  		}
				  	%>
				</select>
				<input id="content" name="content" type="text" class="input-text" value="<%= content %>"/>
				<span class="searchButton" onclick="searchList();"></span>
			</div>
		</div>
		<div class="content">
			<div class="section">Sort by:&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="ranking" id="sortType" onclick="searchList();" name="sortBy" />&nbsp;&nbsp;Ranking &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" value="update_time" onclick="searchList();" id="sortType" name="sortBy" checked="checked"/>&nbsp;&nbsp;Time</div>
			<div class="underline"></div>
			<div id="productArea" class="productArea"></div>
			<div id="pagebar" class="pagebar"></div>
		</div>
    </div>
<div id="templates" style="display: none;">
<script x-id="pagebar" type="text/x-template">
<jsp:include page='../pagebar.jsp'></jsp:include>
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