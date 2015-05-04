<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.easybuy.product.domain.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Easybuy.com</title>
<link href="/Easybuy/css/common.css" rel="stylesheet" type="text/css" />
<link href="/Easybuy/css/product.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/Easybuy/js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="/Easybuy/js/jquery.tmpl.js"></script>
<script type="text/javascript">
	function CheckInputFloat(e)
	{
	    oInput = $(e.target);
		if('' != oInput.val().replace(/\d{1,}\.{0,1}\d{0,}/,''))
	    {
	        oInput.val(oInput.val().match(/\d{1,}\.{0,1}\d{0,}/) == null ? '' :oInput.val().match(/\d{1,}\.{0,1}\d{0,}/));
	    }
	}
</script>
</head>
<%
	Review review = (Review) request.getAttribute("review");
	if(review ==null){
		review = new Review();
	}
	String type = (String)request.getAttribute("type");
	if( type==null)
	{
		type = "new";
	}
	String message = (String)request.getAttribute("message");
	if(message ==null)
	{
		message = "";
	}
%>
<body>
	<div class="container">
		<jsp:include page='../header.jsp'>
			<jsp:param name="select" value="home" />
		</jsp:include>
		<div class="content">
			<div class="title">Product review</div>
			<div class="underline"></div>
			<div class="productForm">
				<form id="form" name="form" method="post" action="/Easybuy/product/review/<%= type.equals("new")?"add":"update" %>">
					<input type="hidden" name="product_id" value="<%= review.getProduct_id()%>"/>
					<input type="hidden" name="product_name" value="<%= review.getProduct_name()%>"/>
					<div class="input-row">
						<div class="label">product</div>
						<div class="input-box"><a href="/Easybuy/product/<%=review.getProduct_id() %>/view" target="_blank"><%= review.getProduct_name() %></a></div>
					</div>
					<div class="input-row">
						<div  class="label">rating</div>
						<div class="input-box"><input name="ranking" id="ranking" type="text" onkeyup="CheckInputFloat(event);" value="<%= review.getRanking() ==0 ? "":review.getRanking() %>" class="input-text" <%= type.equals("new")?"":"disabled" %>/>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="textarea-row">
						<div  class="label">comment</div>
						<div class="input-box"><textarea name="comment" id="comment" rows="5" class="input-textarea"><%= review.getReview() ==null ? "": review.getReview()%></textarea>&nbsp;<font color="red">*</font></div>
					</div>
					<div class="save-box"><span class="span-click-box" onClick="document.form.submit();">Save</span></div>
					<div class="save-info"><%= message %></div>
				</form>
			</div>
		</div>
    </div>
</body>
</html>