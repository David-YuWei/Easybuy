<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/user.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/user/userList_seller.js"></script> 
</head>
<body>
<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">User</div>
			<div class="underline"></div>
			<div class="userArea">
				<div class="leftBoard">
					<ul>
						<li class="selected"><a href="/Easybuy/user/seller">Seller</a></li>
						<li><a href="/Easybuy/user/buyer">Buyer</a></li>
					</ul>
				</div>
				<div class="listArea">
					<div class="tabletop"></div>
					<table border="0" cellpadding="0" cellspacing="0" class="data-table" id="list-table">
						<tr class="data-table-head">
							<td width="10%">user</td>
							<td width="15%">full name</td>
							<td width="30%">email</td>
							<td width="15%">status</td>
							<td width=""></td>
						</tr>
					</table>
					<div id="pagebar" class="pagebar"></div>
				</div>
			</div>
		</div>
    </div>
    <div id="templates" style="display: none;">
<script x-id="pagebar" type="text/x-template">
<jsp:include page='../pagebar.jsp'></jsp:include>
</script>
<script x-id="list" type="text/x-template">
{{each list}}
<tr x-id="{{= user_name}}" class="data-table-row">
	<td align="center"><a href="/Easybuy/user/profile/seller?user_name={{= user_name}}">{{= user_name}}</a></td>
	<td align="center">{{= first_name}} {{= middle_name}} {{= last_name}}</td>
	<td align="center">
		{{= email_id}}
	</td>
	<td align="center">
		{{if status =='0'}}<span style="color:#cccccc;">deleted</span>
		{{else status =='1'}}<span style="color:#009933;">approved</span>
		{{else status =='2'}}<span style="color:red;">submitted</span>
		{{/if}}
	</td>
	<td align="center">
		<a href="javascript:void(0)" onclick="approve('{{= user_name}}');">approve user</a>&nbsp;&nbsp;&nbsp	
		<a href="javascript:void(0)" onclick="decline('{{= user_name}}');">decline user</a>
	</td>
</tr>
{{/each}}
</script>
</div>
</body>
</html>