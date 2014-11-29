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
	
		<!-- Sidebar -->
        <div id="sidebar-wrapper" style="overflow-y:auto;overflow-x:hidden">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">    
                	<div style="color:#999999">Bills</div>
                </li>
                <li class="divider"></li>
                <%
					List<Bill> billList = (List<Bill>) request.getAttribute("BillList");
                	int i = 0;
					for (Bill bill : billList){
						pageContext.setAttribute("bill_name", bill.getName());
						pageContext.setAttribute("bill_amount", bill.getAmount());
						pageContext.setAttribute("bill_id", bill.getId());
						
				%>
					<li>
						<a href="/detailservlet/bill/${bill_id}" class="bill-name">&nbsp;&nbsp;&nbsp;${fn:escapeXml(bill_name)}: ${fn:escapeXml(bill_amount)}</a>
					</li>
				<%
						i++;
						if(i == 5){
							break;
						}
					}
				%>
                <li class="sidebar-brand">
                    <div style="color:#999999">Debts</div>
                </li>
                <li class="divider"></li>
                <%
	                List<Debt> DebtList = (List<Debt>) request.getAttribute("DebtList");
					for (Debt debt: DebtList){
						pageContext.setAttribute("debt_name", debt.getName());
						pageContext.setAttribute("debt_amount", debt.getAmount());
						pageContext.setAttribute("debt_id", debt.getId());
				%>
					<li>
						<a href="/detailservlet/debt/${debt_id}" class="debt-name">&nbsp;&nbsp;&nbsp;${fn:escapeXml(debt_name)}: ${fn:escapeXml(debt_amount)}</a>
					</li>
				<%
					}
				%>
				<li class="sidebar-brand">
                    <div style="color:#999999">Maintenance Requests</div>
                </li>
                <li class="divider"></li>
                <%
	                List<MaintenanceRequest> RequestList = (List<MaintenanceRequest>) request.getAttribute("RequestList");
					for (MaintenanceRequest maintenanceRequest: RequestList){
						pageContext.setAttribute("request_name", maintenanceRequest.getName());
						pageContext.setAttribute("request_priority", maintenanceRequest.getPriority());
						pageContext.setAttribute("req_id", maintenanceRequest.getId());
				%>
					<li>
						<a href="/detailservlet/request/${req_id}" class="req-name">&nbsp;&nbsp;&nbsp;${fn:escapeXml(request_name)}: ${fn:escapeXml(request_priority)}</a>
					</li>
				<%
					}
				%>                            
            </ul>
        </div>
	</body>
</html>