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
	    
		<script src="../../Bootstrap/js/bootstrap.js"></script>
		<link rel="stylesheet" href="RoomLife.css">
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="sidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <% 
                Bill bill = (Bill) request.getAttribute("Bill"); 
                pageContext.setAttribute("bill_name", bill.getName());
                pageContext.setAttribute("bill_amount", bill.getAmount());

                %>
              	<h1>${fn:escapeXml(bill_name)}</h1>
              	<% if(bill.getDatePaid() == null) {
              		pageContext.setAttribute("bill_deadline", bill.getDateDeadline());
              	%>
              		<div>${fn:escapeXml(bill_name)} is due on ${fn:escapeXml(bill_deadline)}</div>
              	<%
              	} else {
                    pageContext.setAttribute("bill_paid", bill.getDatePaid());
                %>
                	<div>${fn:escapeXml(bill_name)} was paid on ${fn:escapeXml(bill_paid)}</div>
                <%
              	}
              	%>
              	<div>Roommates Splitting the Bill</div>
              	<table>
              		<% 
              			ArrayList<String> names = (ArrayList<String>) request.getAttribute("names");
              			ArrayList<Double> amount = (ArrayList<Double>) request.getAttribute("amount");
              			for (int j = 0; j < names.size(); j++){
              				pageContext.setAttribute("name", names.get(j));
              				if (amount.get(j) == 0){
              					pageContext.setAttribute("amount", "paid");
              				} else {
              					pageContext.setAttribute("amount", amount.get(j));
              				}
              			%>
              			<tr>
              				<td>
              					<div>${fn:escapeXml(name)}</div>
              				</td>
              				<td>
              					<div>&nbsp;&nbsp;&nbsp;&nbsp;</div>
              				</td>
              				<td>
              					<div>${fn:escapeXml(amount)}</div>
              				</td>
              			</tr>
              		<%			
              			}
              		%>              		
              	</table>              	
			</div>
		</div>
		</div>
	</body>
</html>