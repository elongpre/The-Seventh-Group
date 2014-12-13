<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.appspot.t_gecko_764.*" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<!-- Bootstrap Core CSS -->
		
	    <link href="Bootstrap/css/bootstrap.css" rel="stylesheet">
	
	    <!-- Sidebar CSS -->
	    <link href="Bootstrap/css/simple-sidebar.css" rel="stylesheet">
	    
	    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="../../RoomLife.css">
		<script src="/Bootstrap/js/bootstrap.js"></script>
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="sidebar.jsp" %> 
        <!-- Page Content -->
        
        <div id="page-content-wrapper">
        	<div class="container-fluid">
            	<%
            		String name = (String) request.getAttribute("UserName");
          			
          			pageContext.setAttribute("user_name", name);
            	%> 
            	
            	<h1 id="username">Hello ${fn:escapeXml(user_name)}!</h1>
			</div>
		</div>
		</div>
	</body>
</html>