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
<script type="text/javascript" src="/Easybuy/js/registration/seller.js"></script>
</head>

<script>  
function validate(){ 
var username=document.myform.username.value; 
var firstname=document.myform.firstname.value;  
var password=document.myform.password.value; 
var lastname=document.myform.lastname.value;  
var cpassword=document.myform.cpassword.value; 
var emailid=document.myform.emailid.value;  
var address=document.myform.address.value;
var phonenumber=document.myform.phonenumber.value;  
var routingnumber=document.myform.routingnumber.value; 
var accountnumber=document.myform.accountnumber.value; 
var atposition=emailid.indexOf("@");  
var dotposition=emailid.lastIndexOf(".");  

if (username==null || username==""){  
	document.getElementById("message").innerHTML="User Name cannot be blank";
    return false;
 
}
if (firstname==null || firstname==""){  
	document.getElementById("message").innerHTML="First Name cannot be blank";
    return false;
  
}
if (lastname==null || lastname==""){  
	  document.getElementById("message").innerHTML="Last Name cannot be blank"; 
	  return false; 
}
if (emailid==null || emailid==""){  
	  document.getElementById("message").innerHTML="Email ID cannot be blank"; 
	  return false; 
}
if (atposition<1 || dotposition<atposition+2 || dotposition+2>=emailid.length){  
	document.getElementById("message").innerHTML="Email ID should be like abc@axy.com"; 
	  return false; 
	  } 
if (address==null || address==""){  
	  document.getElementById("message").innerHTML="Address cannot be blank";
	  return false; 
}
if (phonenumber==null || phonenumber==""){  
	  document.getElementById("message").innerHTML="Phone number cannot be blank"; 
	  return false; 
}
if (isNaN(phonenumber)){  
	  document.getElementById("message").innerHTML="Phone number must contian only numbers";  
	  return false;  
}
if (phonenumber.length != 10){  
	  document.getElementById("message").innerHTML="Phone Numbers should be 10 digits";  
	  return false;  
}
if (password==null || password==""){  
	  document.getElementById("message").innerHTML="Password cannot be blank";
	  return false;
}
if(password.length<8){  
	document.getElementById("message").innerHTML="Password should be at least 8 characters long";
  return false;  
}
if (cpassword==null || cpassword==""){  
	  document.getElementById("message").innerHTML="Please re-enter Password";  
	  return false; 
}
if(cpassword.length<8){  
	document.getElementById("message").innerHTML="Passwords does not match";
  return false;  
 }
if(!password==cpassword){  
	  document.getElementById("message").innerHTML="Passwords does not match";
	  return false;  
} 
if (accountnumber==null || accountnumber==""){  
	  document.getElementById("message").innerHTML="Account Number cannot be blank"; 
	  return false; 
}
if (isNaN(accountnumber)){  
	  document.getElementById("message").innerHTML="Account number must contian only numbers";  
	  return false;  
}
if (routingnumber==null || routingnumber==""){  
	  document.getElementById("message").innerHTML="Routing number cannot be blank"; 
	  return false; 
}
if (isNaN(routingnumber)){  
	  document.getElementById("message").innerHTML="Routing number must contian only numbers";  
	  return false;  
}
}  
</script>  

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
		<div id="message" class="registrationMessage">
		
		</div>
		<div class="registration">
		<div class="registrationtop"></div>
			<form name="myform" onsubmit="return validate()" id="form" method="post" action="/Easybuy/registration/seller/add">
				<div class="registration-form">
					<div class="form-head">
						Seller Registration
					</div>
					<div class="form-row">
						<div class="label">User Name</div>
						<div class="input-box"><input name="username" id="username" type="text" value="${username}" class="input-text"/>&nbsp;<font color="red">*</font>
						</div>
						</div>
						<div class="form-row-info">
						<div class="label"></div>
						<div class="input-box"><a href="javascript:void(0)" onclick="check();">Check username</a></div>
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
						<div class="input-box"><input name="phonenumber"   type="text" value="${phonenumber}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					
					<div class="form-row">
						<div class="label">Password</div>
						<div class="input-box"><input name="password" id="password" type="password" value="${password}"   class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label"> </div>
						<div class="input-box">Must be at least 8 letters with 1 capital letter and 1 number</div>
					</div>
					<div class="form-row">
						<div class="label">Confirm Password</div>
						<div class="input-box"><input name="cpassword" id="cpassword" type="password" value="${cpassword}"  class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label">Account Number</div>
						<div class="input-box"><input name="accountnumber"  type="text" value="${accountnumber}" class="input-text"/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="form-row">
						<div class="label">Routing Number</div>
						<div class="input-box"><input name="routingnumber"  type="text" value="${routingnumber}" class="input-text"/>&nbsp;<font color="red">*</font></div>
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