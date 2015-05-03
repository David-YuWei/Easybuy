<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.easybuy.message.domain.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/message.css" rel="stylesheet" type="text/css" />
</head>
<%
	Message message = (Message) request.getAttribute("messageInfo");
%>
<body>
<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Message</div>
			<div class="underline"></div>
			<div class="userArea">
				<div class="input-row">
					<div class="label">Type</div>
					<div class="label2">
					<% if(message.getType().equals("0")){ %>
						Registration
					<%}else if(message.getType().equals("1")){ %>
						New order
					<%}else if(message.getType().equals("2")){ %>
						Order Update
					<%}else if(message.getType().equals("3")){ %>
						Customer Review
					<%}else if(message.getType().equals("4")){ %>
						User Message
					<%} %>
					</div>
				</div>
				<div class="input-row">
					<div class="label">From</div>
					<div class="label2"><%=message.getFromuser() %></div>
				</div>
				<div class="input-row">
					<div class="label">To</div>
					<div class="label2"><%=message.getTouser() %></div>
				</div>
				<div class="input-row">
					<div class="label">Content</div>
					<div class="label3"><%=message.getContent() %><br><br><br><br></div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>