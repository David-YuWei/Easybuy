<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.easybuy.user.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/user.css" rel="stylesheet" type="text/css" />
</head>
<%
	Seller seller = (Seller) request.getAttribute("sellerInfo");
%>
<body>
   <div class="container">
    <div class="logo">
			<a href="/Easybuy/">
				<img alt="" src="/Easybuy/images/logo.png"  />
			</a>
		</div>
		<div class="myProfile_seller">
			<div class="myProfile_sellertop"></div>
			<form id="form" method="post" action="/Easybuy/profile/myProfile_seller">
				<div class="myProfile_seller-form">
					<div class="form-head">
						<h1>Seller Profile</h1>
					</div>
		
					<div class="form-row">
						<label>First Name</label>
						<input name="firstname" id="firstname" type="text" value="<%=seller.getFirst_name() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Middle Name</label>
						<input name="middlename" id="middlename" type="text" value="<%=seller.getMiddle_name() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Last Name</label>
						<input name="lastname" id="lastname" type="text" value="<%=seller.getLast_name() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Email Id</label>
						<input name="emailid" id="emailid" type="text" value="<%=seller.getEmail_id() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Address</label>
						<input name="address" id="address" type="text" value="<%=seller.getAddress() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Phone Number</label>
						<input name="phonenumber" id="phonenumber" type="text" value="<%=seller.getPhone_number() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>User Name</label>
						<input name="username" id="username" type="text" value="<%=seller.getUser_name() %>" class="input-text"/>
					</div>
					
					<div class="form-row">
						<label>Password</label>
						<input name="password" id="password" type="password" value="<%=seller.getPassword() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Routing Number</label>
						<input name="routingnumber" id="routingnumber" type="text" value="<%=seller.getRouting_number() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Account Number</label>
						<input name="accountnumber" id="accountnumber" type="text" value="<%=seller.getAccount_number() %>" class="input-text"/>
					</div>
					
			</div>
			</form>
			</div>
			</div>
			

</body>
</html>