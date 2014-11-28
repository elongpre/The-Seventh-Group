<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.appspot.t_gecko_764.*" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<!-- Bootstrap Core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet">
	
	    <!-- Sidebar CSS -->
	    <link href="css/simple-sidebar.css" rel="stylesheet">
	    
		<script src="/Bootstrap/js/bootstrap.js"></script>
		
	</head>
	<body>
		<div id="wrapper">
		<!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <a href="/home">
                        Home
                    </a>
                </li>
                <%
					List<Bill> billList = (List<Bill>) request.getAttribute("BillList");
					for(int i = 0; i < 10; i++){
						for (Bill bill : billList){
							pageContext.setAttribute("bill_name", bill.getName());
							pageContext.setAttribute("bill_amount", bill.getAmount());
				%>
					<li>
						<a href="#" class="bill-name">${fn:escapeXml(bill_name)}: ${fn:escapeXml(bill_amount)}</a>
					</li>
				<%
						}
					}
				%>
                <li>
                    <a href="#">Contact</a>
                </li>
            </ul>
        </div>
        
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1> RoomLife: <small>make life simpler</small></h1>
                    </div>
					<div class="container-fluid">
						<div class="col-md-8" style="height:35%; width:25%;overflow:auto;">
							<%
								List<Debt> DebtList = (List<Debt>) request.getAttribute("DebtList");
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
								List<MaintenanceRequest> RequestList = (List<MaintenanceRequest>) request.getAttribute("RequestList");
									for (MaintenanceRequest maintenanceRequest: RequestList){
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