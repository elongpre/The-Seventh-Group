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
			<p>Hello!
			<a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
			to include your name with greetings you post.</p>
			<p> current login emails are alice@example.com, bob@example.com, candice@example.com, and danny@example.com </p>
			<p> the canned data intends only alice and bob to be used - entries are sparce (sparcer) for candice and danny </p>
		<%
		    }
		%>
	
	</body>
	
</html>