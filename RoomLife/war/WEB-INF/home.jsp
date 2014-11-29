<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.appspot.t_gecko_764.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<!-- Bootstrap Core CSS -->
	    <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
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
                <div class="row">                   
					<div class="container-fluid">
						<div class="col-md-8" style="height:35%; width:25%;overflow:auto;">
							<%
								for (Debt debt: DebtList){
									pageContext.setAttribute("debt_name", debt.getName());
									pageContext.setAttribute("debt_amount", debt.getAmount());
							%>
								<p>
									<span class="bill-name">${fn:escapeXml(debt_name)}: ${fn:escapeXml(debt_amount)}</span>
								</p>
							<%
								}
							%>
							
						</div>
					</div>
					<div class="container-fluid">
						<div class="col-md-8" style="height:25%; width:25%;overflow:auto;">
							<%
								List<MaintenanceRequest> RequestList2 = (List<MaintenanceRequest>) request.getAttribute("RequestList");
									for (MaintenanceRequest maintenanceRequest: RequestList2){
										pageContext.setAttribute("request_name", maintenanceRequest.getName());
										pageContext.setAttribute("request_priority", maintenanceRequest.getPriority());
							%>
								<p>
									<span class="bill-name">${fn:escapeXml(request_name)}: ${fn:escapeXml(request_priority)}</span>
								</p>
							<%
								}
							%>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</body>
</html>