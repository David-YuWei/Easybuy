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
<script type="text/javascript" src="/Easybuy/js/user/userList_buyer.js"></script>
</head>

<%
	Buyer buyer = (Buyer) request.getAttribute("buyerInfo");
    
%>
<script>
function validate()
{
var username=document.myform.user_name.value; 
var firstname=document.myform.firstname.value;  
var password=document.myform.password.value; 
var lastname=document.myform.lastname.value;  
var cpassword=document.myform.cpassword.value; 
var emailid=document.myform.emailid.value;  
var address=document.myform.address.value;
var phonenumber=document.myform.phonenumber.value;  
var atposition=emailid.indexOf("@");  
var dotposition=emailid.lastIndexOf(".");  

if (username==null || username==""){  
	alert(username);
	document.getElementById("error").innerHTML="User Name cannot be blank";
    return false;
 
}
if (firstname==null || firstname==""){  
	document.getElementById("error").innerHTML="First Name cannot be blank";
    return false;
  
}
if (lastname==null || lastname==""){  
	  document.getElementById("error").innerHTML="Last Name cannot be blank"; 
	  return false; 
}
if (emailid==null || emailid==""){  
	  document.getElementById("error").innerHTML="Email ID cannot be blank"; 
	  return false; 
}
if (atposition<1 || dotposition<atposition+2 || dotposition+2>=emailid.length){  
	document.getElementById("error").innerHTML="Email ID should be like abc@axy.com"; 
	  return false; 
	  } 
if (address==null || address==""){  
	  document.getElementById("error").innerHTML="Address cannot be blank";
	  return false; 
}
if (phonenumber==null || phonenumber==""){  
	  document.getElementById("error").innerHTML="Phone number cannot be blank"; 
	  return false; 
}
if (isNaN(phonenumber)){  
	  document.getElementById("error").innerHTML="Enter Numeric value only";  
	  return false;  
}
if (phonenumber.length != 10){  
	  document.getElementById("error").innerHTML="Phone Numbers should be 10 didgits";  
	  return false;  
}
}
</script>
<body>
   <div class="container">
    	<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Buyer Profile</div>
			<div class="underline"></div>
			<div class="userForm">
			<form id="form" method="post" onsubmit="validate()" action="/Easybuy/user/profile/updatebuyer">
				<div class="input-row">
						<div class="label">User Name</div>
						<div class="label"><input name="user_name"><%=buyer.getUser_name() %></div>
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
					<div class="input-box">
						<input id="save" type="submit" value="Save" class="input-button"/>
					</div>
				<!-- 	<div class="input-box">
						<input id="delete_user" type="submit" value="Delete" onclick ="del('{{= user_name}}');" class="input-button"/>
					</div>  -->
			</form>
			</div>
      </div>
	</div>		

</body>
</html>