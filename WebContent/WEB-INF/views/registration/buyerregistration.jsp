<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/registration.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/registration/buyer.js"></script>
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
		<div id="message" class="registrationMessage">${msg}</div>
		<div class="registration">
		<div class="registrationtop"></div>
			<form id="form" method="post" action="/Easybuy/registration/buyer/add">
				<div class="registration-form">
					<div class="form-head">
						Buyer Registration
					</div>
					<div class="form-row">
						<div class="label">User Name</div>
						<div class="input-box"><input name="username" id="username" type="text" value="${username}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row-info">
						<div class="label"> </div>
						<div class="input-box"><a href="javascript:void(0);" onclick="check();">Check username</a></div>
					</div>
					<div class="form-row">
						<div class="label">First Name</div>
						<div class="input-box"><input name="firstname" id="firstname" type="text" value="${firstname}" class="input-text" id="inputSuccess"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label">Middle Name</div>
						<div class="input-box"><input name="middlename" id="middlename" type="text" value="${middlename}" class="input-text"/>&nbsp;</div>
					</div>
					<div class="form-row">
						<div class="label">Last Name</div>
						<div class="input-box"><input name="lastname" id="lastname" type="text" value="${lastname}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label">Email Id</div>
						<div class="input-box"><input name="emailid" id="emailid" type="text" value="${emailid}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label">Address</div>
						<div class="input-box"><input name="address" id="address" type="text" value="${address}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label">Phone Number</div>
						<div class="input-box"><input name="phonenumber" id="phonenumber" type="text" value="${phonenumber}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					
					<div class="form-row">
						<div class="label">Password</div>
						<div class="input-box"><input name="password" id="password" type="password" value="${password}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label"> </div>
						<div class="input-box">Must be at least 8 letters with 1 capital letter and 1 number</div>
					</div>
					<div class="form-row">
						<div class="label">Confirm Password</div>
						<div class="input-box"><input name="cpassword" id="cpassword" type="password" value="${cpassword}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-submit">
						<input id="signin" type="submit" value="Register" class="input-button"/>
					</div>
				</div>
			</form>
		</div>
    </div>
</body>
</html>