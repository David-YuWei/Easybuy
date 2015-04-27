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
	Admin admin = (Admin) request.getAttribute("adminInfo");
	if(admin.getMiddle_name() == null){
		admin.setMiddle_name("");
	}
	else{
		
	}
%>
<body>
   <div class="container">
	    <jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Admin Profile</div>
			<div class="underline"></div>
			<div class="userForm">
			<form id="form" method="post" action="/Easybuy/profile/myProfile_admin">
				<div class="myProfile_admin-form">
					<div class="input-row">
						<div class="label">User Name</div>
						<div class="input-box"><input name="username" id="username" type="text" value="<%=admin.getUser_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">First Name</div>
						<div class="input-box"><input name="firstname" id="firstname" type="text" value="<%=admin.getFirst_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="input-row">
						<div class="label">Middle Name</div>
						<div class="input-box"><input name="middlename" id="middlename" type="text" value="<%=admin.getMiddle_name() %>" class="input-text"/>&nbsp;</div>
					</div>
					<div class="input-row">
						<div class="label">Last Name</div>
						<div class="input-box"><input name="lastname" id="lastname" type="text" value="<%=admin.getLast_name() %>" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>

					<!--  
					<div class="form-row">
						<label>Password</label>
						<input name="password" id="password" type="password" value="<%= admin.getPassword() %>" class="input-text"/>
					</div>
					-->
			</div>
			</form>
			</div>
			</div>
	</div>
</body>
</html>