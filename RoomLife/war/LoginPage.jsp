<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="RoomLife.css">
		<title>Welcome!</title>
	</head>
	
	<body>
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    if (user != null) {
		      pageContext.setAttribute("user", user);
		      response.sendRedirect("/home");
			} else {
		%>
			<h1>Welcome to RoomLife!</h1>
			<a href="<%= userService.createLoginURL(request.getRequestURI()) %>"><button>Sign in</button></a>
			<p>New user? Click <a href="registerUser.jsp">here</a> to register.</p>
		<%
		    }
		%>
	
	</body>
	
</html>