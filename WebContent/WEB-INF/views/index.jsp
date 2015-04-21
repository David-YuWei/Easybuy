<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
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
					<select class="list-select">
					  	<option value="">All</option>
					  	<option value="Apple">Apple</option>
					  	<option value="dell">Dell</option>
					</select>
					<input name="searchText" type="text" class="input-text"/>
					<span class="searchButton" onclick="document.searchForm.submit();"></span>
				</form>
			</div>
		</div>
		<div class="content">
			<div class="section">Hot product</div>
			<div class="underline"></div>
			<div class="productArea"></div>
			<div class="section">New product</div>
			<div class="underline"></div>
			<div class="productArea"></div>
		</div>
    </div>
</body>
</html>