<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/user.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <div class="container">
    <div class="logo">
			<a href="/Easybuy/">
				<img alt="" src="/Easybuy/images/logo.png"  />
			</a>
		</div>
		<div class="myProfile_buyer">
			<div class="myProfile_buyertop"></div>
			<form id="form" method="post" action="/Easybuy/profile/myProfile_buyer">
				<div class="myProfile_buyer-form">
					<div class="form-head">
						<h1>Login</h1>
					</div>
		
					<div class="form-row">
						<label>First Name</label>
						<input name="firstname" id="firstname" type="text" value="${first_name}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Middle Name</label>
						<input name="middlename" id="middlename" type="text" value="${middle_name}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Last Name</label>
						<input name="lastname" id="lastname" type="text" value="${last_name}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Email Id</label>
						<input name="emailid" id="emailid" type="text" value="${email_id}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Address</label>
						<input name="address" id="address" type="text" value="${address}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Phone Number</label>
						<input name="phonenumber" id="phonenumber" type="text" value="${phone_number}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>User Name</label>
						<input name="username" id="username" type="text" value="${user_name}" class="input-text"/>
					</div>
					
					<div class="form-row">
						<label>Password</label>
						<input name="password" id="password" type="password" value="${password}" class="input-text"/>
					</div>
					
			</div>
			</form>
			</div>
			</div>
			

</body>
</html>