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
	Buyer buyer = (Buyer) request.getAttribute("buyerInfo");
	if(buyer.getMiddle_name() == null){
		buyer.setMiddle_name("");
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
			<form id="form" method="post" action="/Easybuy/profile/myProfile_buyer">
				<div class="input-row">
						<div class="label">User Name</div>
						<div class="input-box"><input name="username" id="username" type="text" value="<%=buyer.getUser_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">First Name</div>
						<div class="input-box"><input name="firstname" id="firstname" type="text" value="<%=buyer.getFirst_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Middle Name</div>
						<div class="input-box"><input name="middlename" id="middlename" type="text" value="<%=buyer.getMiddle_name() %>" class="input-text"/>&nbsp;</div>
					</div>
					<div class="input-row">
						<div class="label">Last Name</div>
						<div class="input-box"><input name="lastname" id="lastname" type="text" value="<%=buyer.getLast_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Email Id</div>
						<div class="input-box"><input name="emailid" id="emailid" type="text" value="<%=buyer.getEmail_id() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Address</div>
						<div class="input-box"><input name="address" id="address" type="text" value="<%=buyer.getAddress() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Phone Number</div>
						<div class="input-box"><input name="phonenumber" id="phonenumber" type="text" value="<%=buyer.getPhone_number() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<!--  
					<div class="input-row">
						<div class="label">Password</div>
						<div class="input-box"><input name="password" id="password" type="password" value="<%=buyer.getPassword() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					-->
			</form>
			</div>
      </div>
	</div>		

</body>
</html>