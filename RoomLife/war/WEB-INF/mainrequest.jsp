<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.appspot.t_gecko_764.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<!-- Bootstrap Core CSS -->
	    <link href="../../Bootstrap/css/bootstrap.css" rel="stylesheet">
	
	    <!-- Sidebar CSS -->
	    <link href="../../Bootstrap/css/simple-sidebar.css" rel="stylesheet">
	    
	    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="../../RoomLife.css">
		
		<script src="../../Bootstrap/js/bootstrap.js"></script>
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="sidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <h1>Submit Maintenance Request</h1>
	    
			    <form action="mreq" method="POST">
			    	<fieldset>
			    		<legend>Description of Issue</legend>
			    		
			    		<label class="field" for="Name">Select Category:</label>
			    		<p><select name="name">
						  <option value="plumbing">Plumbing</option>
						  <option value="electricity">Electricity</option>
						  <option value="pest control">Pest Control</option>
						</select></p>
		
				        <label class="field" for="Priority">Priority:</label>
				        <p><input type="text" name="priority"></p>
				        
				        <label class="field" for="Place">Place:</label>
				        <p><input type="text" name="place"></p>
				        
				        <label class="field" for="Group">Group:</label>
				        <p><input type="text" name="group"></p>
				        
				        <label class="field" for="Description">Description:</label>
				        <p><textarea rows="10" cols="50" name="description"></textarea></p>
				        
				        <input type="submit" value="Submit">
			        </fieldset>
			    </form>
			</div>
		</div>
		</div>
	</body>
</html>