<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="container">
		<div class="logo">
			<a href="/Easybuy/">
				<img alt="" src="/Easybuy/images/logo.png"  />
			</a>
		</div>
		<div class="login">
	${msg}
		<div class="logintop"></div>
			<form id="form" method="post" action="/Easybuy/login/signin">
				<div class="login-form">
					<div class="form-head">
						<h1>Login</h1>
					</div>
					<div class="form-row">
						<label>Username</label>
						<input name="username" id="username" type="text" value="${username}" class="input-text"/>
					</div>
					<div class="form-row">
						<label>Password</label>
						<input name="password" id="password" type="password" value="${password}" class="input-text"/>
					</div>
					<div class="form-row">
						<input id="signin" type="submit" value="Sign in" class="input-button"/>
					</div>
					<div class="form-row">
						<div class="right"><a class="right" href="/Easybuy/registration/seller">Register as Seller</a></div>
						<div class="left"><a class ="left" href="/Easybuy/registration/buyerregistration" >Register as Buyer</a></div>
					</div>
				</div>
			</form>
		</div>
    </div>
</body>
</html>