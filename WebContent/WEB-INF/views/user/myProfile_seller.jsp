<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.easybuy.user.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/user.css" rel="stylesheet" type="text/css" />
</head>
<%
	Seller seller = (Seller) request.getAttribute("sellerInfo");
	if(seller.getMiddle_name() == null){
		seller.setMiddle_name("");
	}
%>
<body>
   <div class="container">
    	<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Seller Profile</div>
			<div class="underline"></div>
			<div class="userForm">
			<form id="form" method="post" action="/Easybuy/profile/myProfile_seller">
					<div class="input-row">
						<div class="label">User Name</div>
						<div class="input-box"><input name="username" id="username" type="text" value="<%=seller.getUser_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">First Name</div>
						<div class="input-box"><input name="firstname" id="firstname" type="text" value="<%=seller.getFirst_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Middle Name</div>
						<div class="input-box"><input name="middlename" id="middlename" type="text" value="<%=seller.getMiddle_name() %>" class="input-text"/>&nbsp;</div>
					</div>
					<div class="input-row">
						<div class="label">Last Name</div>
						<div class="input-box"><input name="lastname" id="lastname" type="text" value="<%=seller.getLast_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Email Id</div>
						<div class="input-box"><input name="emailid" id="emailid" type="text" value="<%=seller.getEmail_id() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Address</div>
						<div class="input-box"><input name="address" id="address" type="text" value="<%=seller.getAddress() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Phone Number</div>
						<div class="input-box"><input name="phonenumber" id="phonenumber" type="text" value="<%=seller.getPhone_number() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					
					<!--  
					<div class="input-row">
						<div class="label">Password</div>
						<div class="input-box"><input name="password" id="password" type="password" value="<%=seller.getPassword() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Routing Number</div>
						<div class="input-box"><input name="routingnumber" id="routingnumber" type="text" value="<%=seller.getRouting_number() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Account Number</div>
						<div class="input-box"><input name="accountnumber" id="accountnumber" type="text" value="<%=seller.getAccount_number() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					-->
			</form>
			</div>
			</div>
	</div>

</body>
</html>