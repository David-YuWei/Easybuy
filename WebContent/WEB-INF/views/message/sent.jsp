<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sent</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/message.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/wishlist.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="/Easybuy/js/message/sentList.js"></script>
</head>

<body>
<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Sent</div>
			<div class="underline"></div>
			<div class="userArea">
				<div class="leftBoard">
					<ul>
						<li onClick="window.location.href = '/Easybuy/message/';">Received</li>
						<li class="selected" onClick="window.location.href = '/Easybuy/message/sent';">Sent</li>
					</ul>
				</div>
				
				<div class="listArea">
					<div class="link">
						<a href="/Easybuy/message/message_new">New Message</a>
					</div>
					<div class="tabletop"></div>
					<table border="0" cellpadding="0" cellspacing="0" class="data-table" id="list-table">
						<tr class="data-table-head">
							<td width="20%">To</td>
							<td width="60%">Content</td>
							<td width="20%"></td>
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

<script x-id="sentMessages" type="text/x-template">

{{each list}}
<tr x-id="{{= message_id}}" class="data-table-row">
	<td align="center">{{= touser}}</td>
	<td align="left">{{= content}}</td>
	<td align="center">
	<a href="/Easybuy/message/message_view?message_id={{= message_id}}">View</a></td>
</tr>
{{/each}}



</script>
</div>
 
</body>
</html>