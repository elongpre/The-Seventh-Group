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
                String debtor = (String) request.getAttribute("debtor");
                List<Debt> debtList = (List<Debt>) request.getAttribute("DebtList");
                pageContext.setAttribute("debtor", debtor);                
                %>
                <h1>${fn:escapeXml(debtor)}</h1> 
               
              	<% for(Debt debt: debtList){
              			pageContext.setAttribute("debt_name", debt.getName());
              			pageContext.setAttribute("debt_amount", debt.getAmount());
              	%>
              		<div>${fn:escapeXml(debt_name)}: ${fn:escapeXml(debt_amount)}</div>
              	<%
              	}
              	%>
                 
			</div>
		</div>
		</div>
	</body>
</html>