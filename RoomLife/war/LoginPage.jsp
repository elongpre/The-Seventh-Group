<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.appspot.t_gecko_764.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="RoomLife.css">
		<title>Welcome!</title>
	</head>
	
	<body>
		<%
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    if (user != null) {
		    	DataStore datastore = DataStore.getInstance();
		    	if((datastore.getPerson(user.getEmail()) == null) && (datastore.getLandlord(user.getEmail()) == null)){
		    		response.sendRedirect(userService.createLogoutURL("/LoginPage.jsp"));
		    	} else {
			      	pageContext.setAttribute("user", user);
			      	response.sendRedirect("/home");
		    	}
			} else {
		%>
			<h1>Welcome to RoomLife!</h1>
			<a href="<%= userService.createLoginURL(request.getRequestURI()) %>"><button name="signIn" class="button">Sign in</button></a>
			<br>
			<p>New user? Click <a href="registerUser.jsp?failed=no" id="register">here</a> to register.</p>
		<%
		    }
		%>
	
	</body>
	
</html>