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
                	<table width="100%">
                		<tr>
                			<td>
                				<div class="sidebar-header" style="height: 20px">
			               		</div>
                			</td>
                			<td align="center">
                			</td>
                		</tr>
                		<tr>
                			<td>
                				<div class="sidebar-header">
			                		Bills
			               		</div>
                			</td>
                			<td align="right" >
                				<a href="/entry/bill" style="padding-right: 10px">
               						<span class="glyphicon glyphicon-plus"></span>
               					</a>            				
                			</td>
                		</tr>
                	</table>                  	
                </li>
                <li class="divider"></li>
                <%
					List<Bill> billList = (List<Bill>) request.getAttribute("BillList");
                	if (billList!=null){
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
                		}
				%>
                <li class="sidebar-brand">
                    <table width="100%">
                		<tr>
                			<td>
                				<div class="sidebar-header" style="height: 20px">
			               		</div>
                			</td>
                			<td align="center">
                			</td>
                		</tr>
                		<tr>
                			<td>
                				<div class="sidebar-header">
			                		Balances
			               		</div>
                			</td>
                			<td align="right" >
                				<a href="/entry/debt" style="padding-right: 10px">
               						<span class="glyphicon glyphicon-plus"></span>
               					</a>            				
                			</td>
                		</tr>
                	</table>
                </li>
                <li class="divider"></li>
                <%
                	
	                	List<Debt> DebtList = (List<Debt>) request.getAttribute("DebtList");
               			if (DebtList!=null){
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
                		}
	                List<String> DebtNames = (List<String>) request.getAttribute("DebtNames"); 
                	List<Double> DebtAmounts = (List<Double>) request.getAttribute("DebtAmounts");  
                	int k = 0;
					for (String name : DebtNames){
						
						pageContext.setAttribute("debt_name", name);
						pageContext.setAttribute("debt_amount", String.format("%.2f", DebtAmounts.get(k)));
						if(DebtAmounts.get(k) < 0){				
				%>
							<li>
								<a href="/detailservlet/debt/${debt_name}" class="debt-name">
									&nbsp;&nbsp;&nbsp;${fn:escapeXml(debt_name)}: <span style="color: red">${fn:escapeXml(debt_amount)}</span>
								</a>
							</li>
				<%
						} else {
				%>
							<li>
								<a href="/detailservlet/debt/${debt_name}" class="debt-name">
									&nbsp;&nbsp;&nbsp;${fn:escapeXml(debt_name)}: <span style="color: green">${fn:escapeXml(debt_amount)}</span>
								</a>
							</li>
				<%
						}
						k++;
					}
				%>
				<li class="sidebar-brand">
                    <table width="100%">
                		<tr>
                			<td>
                				<div class="sidebar-header" style="height: 20px">
			                		Maintenance
			               		</div>
                			</td>
                			<td align="center">
                			</td>
                		</tr>
                		<tr>
                			<td>
                				<div class="sidebar-header">
			                		Requests
			               		</div>
                			</td>
                			<td align="right" >
                				<a href="/entry/request" style="padding-right: 10px">
               						<span class="glyphicon glyphicon-plus"></span>
               					</a>            				
                			</td>
                		</tr>
                	</table>
                </li>
                <li class="divider"></li>
                <%
                	
	                List<MaintenanceRequest> RequestList = (List<MaintenanceRequest>) request.getAttribute("RequestList");
                	if(RequestList!=null){
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
                	}
				%>                            
            </ul>
        </div>
	</body>
</html>