<%@page import="com.easybuy.user.domain.*"%>
<div class='navigate'>
<ul>
<% 
	User user = (User) session.getAttribute("user");
	if(user == null){
%>
	<li><a href='/Easybuy/login'>Sign in</a></li>
	<li><a href='/Easybuy/registration/buyer'>Register as Buyer</a></li>
	<li><a href='/Easybuy/registration/seller'>Register as Seller</a></li>
	<li class="right"><a href='/Easybuy/order/'>Order</a></li>
	<li class="right"><a href='/Easybuy/shopcart'>Shopcart</a></li>
	<li class="right"><a href='/Easybuy/wishlist'>Wishlist</a></li>
	<li class="right"><a href='/Easybuy/'>Easybuy</a></li>
<%
	}
	else if(user instanceof Buyer){
%>
		<li><a href='/Easybuy/user/profile/buyer?user_name=<%= user.getUser_name() %>'>hello, <%= user.getFirst_name() %></a></li>
		<li><a href='/Easybuy/logout'>Sign out</a></li>
		<li><a href='/Easybuy/message/'>Message</a></li>
		<li class="right"><a href='/Easybuy/order/'>Order</a></li>
		<li class="right"><a href='/Easybuy/shopcart'>Shopcart</a></li>
		<li class="right"><a href='/Easybuy/wishlist'>Wishlist</a></li>
		<li class="right"><a href='/Easybuy/'>Easybuy</a></li>
<%	
	}
	else if(user instanceof Seller){
%>
		<li><a href='/Easybuy/user/profile/seller?user_name=<%= user.getUser_name() %>'>hello, <%= user.getFirst_name() %></a></li>
		<li><a href='/Easybuy/logout'>Sign out</a></li>
		<li><a href='/Easybuy/product/sellerProducts'>My products</a></li>
		<li><a href='/Easybuy/message/'>Message</a></li>
		<li class="right"><a href='/Easybuy/order/'>Order</a></li>
		<li class="right"><a href='/Easybuy/'>Easybuy</a></li>
<%
	}
	else {
%>
		<li><a href='/Easybuy/user/profile/admin?user_name=<%= user.getUser_name() %>'>hello, <%= user.getFirst_name() %></a></li>
		<li><a href='/Easybuy/logout'>Sign out</a></li>
		<li><a href='/Easybuy/message/'>Message</a></li>
		<li class="right"><a href='/Easybuy/user/'>User</a></li>
		<li class="right"><a href='/Easybuy/order/'>Order</a></li>
		<li class="right"><a href='/Easybuy/'>Easybuy</a></li>
<%		
	}
%>
	
</ul>
</div>
