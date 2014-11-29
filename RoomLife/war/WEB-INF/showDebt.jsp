<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.appspot.t_gecko_764.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<!-- Bootstrap Core CSS -->
	    <link href="Bootstrap/css/bootstrap.css" rel="stylesheet">
	
	    <!-- Sidebar CSS -->
	    <link href="Bootstrap/css/simple-sidebar.css" rel="stylesheet">
	    
		<script src="/Bootstrap/js/bootstrap.js"></script>
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="sidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                Welcome to RoomLife!
                <% 
                Debt debt = (Debt) request.getAttribute("Debt");
                pageContext.setAttribute("debt_name", debt.getName());
                pageContext.setAttribute("debt_amount", debt.getAmount());
                
                %>
                <h1>${fn:escapeXml(debt_name)}</h1> 
               
              	<% if(debt.getDatePaid() == null) {
              		
              	%>
              		<div>${fn:escapeXml(debt_name)} has not been paid</div>
              	<%
              	} else {
                    pageContext.setAttribute("bill_paid", debt.getDatePaid());
                %>
                	<div>${fn:escapeXml(bill_name)} was paid on ${fn:escapeXml(bill_paid)}</div>
                <%
              	}
              	%>
              	
              	<div>Debtor</div>
              	<table>
              	<%
              	String name = (String) request.getAttribute("debtor");
              	pageContext.setAttribute("name", name);
              	%>
              	
              	<tr>
              		<td>
              			<div>${fn:escapeXml(name)}  </div>
              		</td>
              		
              		<td>
              			<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
              		</td>
              		<td>
              			<div>${fn:escapeXml(debt_amount) }</div>
              		</td>
              	</tr>
              	</table>
                 
			</div>
		</div>
		</div>
	</body>
</html>