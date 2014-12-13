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
	    <script src="http://code.jquery.com/jquery.js"></script>
		<script src="../../Bootstrap/js/bootstrap.js"></script>
		
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="../../RoomLife.css">
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="sidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <% 
                MaintenanceRequest mainReq = (MaintenanceRequest) request.getAttribute("Request");
                pageContext.setAttribute("req_name", mainReq.getName());
                pageContext.setAttribute("req_date", new SimpleDateFormat("MMM dd").format(mainReq.getDateCreated()));
                pageContext.setAttribute("req_location", mainReq.getLocation());
                pageContext.setAttribute("req_description", mainReq.getDetails());
                pageContext.setAttribute("req_priority",mainReq.getPriority());
                
                %>
                <h1>${fn:escapeXml(req_name)}<span style="float:right">Maintenance Request</span></h1> 
               
              	<% if(mainReq.getCompleted() == null) {             		
              	%>
              		<div><span class="glyphicon glyphicon-asterisk"></span> Maintenance has not been completed</div>
              	<%
              	} else {
                    pageContext.setAttribute("req_complete", mainReq.getCompleted());
                %>
                	<div><span class="glyphicon glyphicon-ok-circle"></span>Maintenance was completed on ${fn:escapeXml(req_complete)}</div>
                <%
              	}
              	String name = (String) request.getAttribute("debtor");
              	pageContext.setAttribute("name", name);              	
              	%>
              	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	              	<div class="panel panel-default">
			    		<div class="panel-heading" role="tab" id="headingFirst">
			      			<h3 class="panel-title">
			        			<a data-toggle="collapse" data-parent="#accordion" href="#collapseFirst" aria-expanded="true" aria-controls="collapseFirst">
          							<div>Maintenance Request Info<span class="glyphicon glyphicon-plus" style="float: right"></span></div>
          						</a>
       						</h3>
       					</div>
       					<div id="collapseFirst" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseFirst">
				      		<ul class="list-group">
				      			<li class="list-group-item">Date Submitted: ${fn:escapeXml(req_date)}</li>
				      			<li class="list-group-item">Priority: ${fn:escapeXml(req_priority)}</li>
				      			<li class="list-group-item">Location: ${fn:escapeXml(req_location)}</li>
				      			<li class="list-group-item">
				      				<div>Details</div>
			      					<div style = "margin:20px; padding:20px" >${fn:escapeXml(req_description)}</div>
			      				</li>
               				</ul>
           				</div>
       				</div>
   				</div>
			</div>
		</div>
		</div>
	</body>
</html>