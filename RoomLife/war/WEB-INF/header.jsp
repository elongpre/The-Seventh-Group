
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.google.appengine.api.users.*" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<html>
  	<link rel="stylesheet" href="/Bootstrap/css/bootstrap.css" />
  	<link rel="stylesheet" href="Blog.css" />
  	<script src="http://code.jquery.com/jquery.js"></script>
 	<script src="/Bootstrap/js/bootstrap.js"></script>
 	
 	<div class="page-header">
 		<nav class="navbar navbar-default navbar-static">
 			<div class="container-fluid">
		       	<div class="navbar-header">
		       		<h1>
			       		<a href="/home" style="color: black">RoomLife: </a>
			       		<small>make life simpler</small>
	       			</h1>
	       		</div>
				<div class="collapse navbar-collapse">
					<%
				    UserService userService = UserServiceFactory.getUserService();
				    User user = userService.getCurrentUser();
				    if (user == null) {
				    	response.sendRedirect("/LoginPage.jsp");
				    } else {
				      	pageContext.setAttribute("user", user);
					%>
						<ul id = "menu" class="nav navbar-nav navbar-right">
							<li id ="dropdown"><a id="signout" href="">${fn:escapeXml(user)}</a>
			    				<ul id ="menu">
			    					<li id="dropdown"><a id="signout" href="<%= userService.createLogoutURL("/LoginPage.jsp") %>">Sign out</a></li>
			    				</ul>
			    			</li>
			   			</ul>				 			
					<% } %>
				</div>
			</div>
		</nav>
	</div>
</html>