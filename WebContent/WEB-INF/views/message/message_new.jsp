<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.easybuy.message.domain.Message, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Message</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/message.css" rel="stylesheet" type="text/css" />

</head>

<%
	Message message = (Message) request.getAttribute("message");
	String disable = (String) request.getAttribute("disable");
	if(disable ==null){
		disable="";
	}
	int message_id;
	String fromuser = "";
	String touser = "";
	String type = "";
	String content = "";
	if(message !=null){
		message_id = message.getMessage_id();
		fromuser = message.getFromuser();
		touser = String.valueOf(message.getTouser());
		type = message.getType();
		content = message.getContent();
		if(content==null){
			content="";
		}
	}
	String error = (String)request.getAttribute("error");
	if(error ==null)
	{
		error = "";
	}
%>

<body>
<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">New Message</div>
			<div class="underline"></div>
			<div class="userArea">
			<div class="messageForm">
				<form id="form" name="form" method="post" action="/Easybuy/message/messageNew">
				<div class="input-row">
					<div class="label">To</div>
					<%if(disable.equals("disable"))
						{%>
					<div class="input-box"><input name="touser" id="touser" type="text" value="<%= touser%>" class="input-text" readonly=readonly/></div>
					<%} %>
					<%if(disable.equals("")){ %>
					<div class="input-box"><input name="touser" id="touser" type="text" value="<%= touser%>" class="input-text" /></div>
					<%} %>
				</div>
				<div class="textarea-row">
					<div class="label">Content</div>
					<div class="input-box"><textarea name="content" rows="5" id="content" class="input-textarea"><%= content%></textarea></div>
					
				</div>
				<div class="save-box"><span class="span-click-box" onClick="document.form.submit();">Send</span></div>
				<div class="save-info"><%= error %></div>
			</form>
			</div>
			</div>
		</div>
</body>
</html>