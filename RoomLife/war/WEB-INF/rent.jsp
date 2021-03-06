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
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	  	<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  		<script>
  			$(function() {
    			$( "#datepicker" ).datepicker();
  			});
	  	</script>
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="landlordSidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <h1>Submit Bills and Debts</h1>
	    
	    <%
	    Long id = (Long) request.getAttribute("id");
	    %>
			    <form action="/rent/${id} " method="POST">
			    	<fieldset>
			    		<legend>Rent</legend>
			    		
				        <label class="field" for="Amount">Amount:</label>
				        <p><input type="text" name="billAmount"></p>
				        <label class="field" for="Amount">Due on:</label>
					    <p><input type="text" name="billDate" id="datepicker"></p>
				        <input type="submit" name="submitBill" value="Submit">
			        </fieldset>
			    </form>
			    
			    <br>
			    <br>
			</div>
		</div>
		</div>
	</body>
</html>
