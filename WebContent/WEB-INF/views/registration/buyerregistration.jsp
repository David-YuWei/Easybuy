<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/registration.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="logo">
			<a href="/Easybuy/">
				<img alt="" src="/Easybuy/images/logo.png"  />
			</a>
		</div>
		<div class="registrationMessage">${msg}</div>
		<div class="registration">
		<div class="registrationtop"></div>
			<form id="form" method="post" action="/Easybuy/registration/buyer/add">
				<div class="registration-form">
					<div class="form-head">
						<h1>Buyer Registration</h1>
					</div>
					<div class="form-row">
						<label>User Name</label>
						<input name="username" id="username" type="text" value="${username}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					<div class="form-row">
						<label>First Name</label>
						<input name="firstname" id="firstname" type="text" value="${firstname}" class="input-text" id="inputSuccess"/>&nbsp;<font color="red">*</font></font>
					</div>
					<div class="form-row">
						<label>Middle Name</label>
						<input name="middlename" id="middlename" type="text" value="${middlename}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Last Name</label>
						<input name="lastname" id="lastname" type="text" value="${lastname}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					<div class="form-row">
						<label>Email Id</label>
						<input name="emailid" id="emailid" type="text" value="${emailid}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					<div class="form-row">
						<label>Address</label>
						<input name="address" id="address" type="text" value="${address}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					<div class="form-row">
						<label>Phone Number</label>
						<input name="phonenumber" id="phonenumber" type="text" value="${phonenumber}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					
					
					<div class="form-row">
						<label>Password</label>
						<input name="password" id="password" type="password" value="${password}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					<div class="form-row">
						<label>Confirm Password</label>
						<input name="cpassword" id="cpassword" type="password" value="${cpassword}" class="input-text"/>&nbsp;<font color="red">*</font>
					</div>
					<div class="form-row">
						<input id="signin" type="submit" value="Register" class="input-button"/>
					</div>
				</div>
			</form>
		</div>
    </div>
</body>
</html>