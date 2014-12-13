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
                MaintenanceRequest mainReq = (MaintenanceRequest) request.getAttribute("Request");
                pageContext.setAttribute("req_name", mainReq.getName());
                pageContext.setAttribute("req_date", new SimpleDateFormat("MMM dd").format(mainReq.getDateCreated()));
                pageContext.setAttribute("req_location", mainReq.getLocation());
                pageContext.setAttribute("req_description", mainReq.getDetails());
                pageContext.setAttribute("req_priority",mainReq.getPriority());
                
                %>
                <h1>${fn:escapeXml(debt_name)}<span style="float:right">Maintenance Request</span></h1> 
               
              	<% if(mainReq.getCompleted() == null) {
              		
              	%>
              		<div>${fn:escapeXml(req_name)} has not been completed</div>
              	<%
              	} else {
                    pageContext.setAttribute("req_complete", mainReq.getCompleted());
                %>
                	<div>${fn:escapeXml(req_name)} was completed on ${fn:escapeXml(req_complete)}</div>
                <%
              	}
              	%>
              	
              	<div>Maintenance Request Info</div>
              	
              	<%
              	String name = (String) request.getAttribute("debtor");
              	pageContext.setAttribute("name", name);              	
              	%>
              	<table>
              		<tr>
              			<td>
              				<div>${fn:escapeXml(req_priority)}</div>
              			</td>
              			<td>
              				<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
              			</td>
              			<td>
              				<div>${fn:escapeXml(req_location)}</div>
              			</td>
              			<td>
              				<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
              			</td>
              			<td>
              				<div>${fn:escapeXml(req_date)}</div>
              			</td>
              			<td>
              				<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
              			</td>

              			<td>
              				<div style = "margin:20px; padding:20px" >${fn:escapeXml(req_description)}</div>
              			</td>
              		</tr>
              	
              	</table>
                
                
			</div>
		</div>
		</div>
	</body>
</html>