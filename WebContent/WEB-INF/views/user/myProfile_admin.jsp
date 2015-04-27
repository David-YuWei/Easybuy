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
%>
<body>
   <div class="container">
    <div class="logo">
			<a href="/Easybuy/">
				<img alt="" src="/Easybuy/images/logo.png"  />
			</a>
		</div>
		<div class="myProfile_admin">
			<div class="myProfile_admintop"></div>
			<form id="form" method="post" action="/Easybuy/profile/myProfile_admin">
				<div class="myProfile_admin-form">
					<div class="form-head">
						<h1>Admin Profile</h1>
					</div>
		            <div class="form-row">
						<label>User Name</label>
						<input name="username" id="username" type="text" value="<%= admin.getUser_name() %>" class="input-text"/>
					</div>
					
					<div class="form-row">
						<label>Password</label>
						<input name="password" id="password" type="password" value="<%= admin.getPassword() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>First Name</label>
						<input name="firstname" id="firstname" type="text" value="<%= admin.getFirst_name() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Middle Name</label>
						<input name="middlename" id="middlename" type="text" value="<%= admin.getMiddle_name() %>" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Last Name</label>
						<input name="lastname" id="lastname" type="text" value="<%= admin.getLast_name() %>" class="input-text"/>
					</div>
			</div>
			</form>
			</div>
			</div>

</body>
</html>