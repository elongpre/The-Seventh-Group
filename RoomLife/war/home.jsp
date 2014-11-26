<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<<<<<<< HEAD
<%@ page import="com.appspot.t_gecko_764.Bill" %>
=======
<%@ page import="com.appspot.t_gecko_764.*" %>
>>>>>>> ffab7458eed4e1f0adc621b5f5f2d9170f7510fa
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<head>
		<link rel="stylesheet" href="/Bootstrap/css/bootstrap.css" />
		<script src="/Bootstrap/js/bootstrap.js"></script>
	</head>
	<body>
		<div class="page-header">
			<h1> RoomLife: <small>make life simpler</small></h1>			
		</div>
		<div class="container-fluid">
			<div class="col-md-8">
				<%
					List<Bill> billList = HomeServlet.getBills();;
						for (Bill bill : billList){
							pageContext.setAttribute("bill_name", bill.getName());
				%>
					<p>
						<span class="bill-name">${fn:escapeXml(bill_name)}</span>
					</p>
				<%
					}
				%>
				
			</div>
		</div>
		<div class="container-fluid">
			<div class="col-md-8">
				<%
					List<Debt> debtList = HomeServlet.getDebts();
						for (Bill bill : billList){
							pageContext.setAttribute("bill_name", bill.getName());
				%>
					<p>
						<span class="bill-name">${fn:escapeXml(bill_name)}</span>
					</p>
				<%
					}
				%>
				
			</div>
		</div>
		<div class="container-fluid">
			<div class="col-md-8">
				<%
					List<MaintenanceRequest> requestList = HomeServlet.getRequests();
						for (Bill bill : billList){
							pageContext.setAttribute("bill_name", bill.getName());
				%>
					<p>
						<span class="bill-name">${fn:escapeXml(bill_name)}</span>
					</p>
				<% } %>
				
			</div>
		</div>
	</body>
</html>