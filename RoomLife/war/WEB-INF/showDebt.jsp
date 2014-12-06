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
        		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<% 
	                String debtor = (String) request.getAttribute("debtor");
					String userEmail = (String) request.getAttribute("UserEmail");
	                List<Debt> debtList = (List<Debt>) request.getAttribute("DebtListz");
	                Double balance = (Double) request.getAttribute("Amount");
	                pageContext.setAttribute("balance", String.format("%.2f", balance));
	                pageContext.setAttribute("debtor", debtor); 
	                int num = 0;
	                if(balance < 0){
                %>
	                	<h1>${fn:escapeXml(debtor)}<span style="float:right;">Balance: <span style="color:red">${fn:escapeXml(balance)}</span></span></h1> 
			  	<% 
	                } else {
               	%>
	                	<h1>${fn:escapeXml(debtor)}<span style="float:right;">Balance: <span style="color:green">${fn:escapeXml(balance)}</span></span></h1> 
			  	<%   	
	                }
			  		for(Debt debt: debtList){
              			pageContext.setAttribute("debtz_name", debt.getName());
              			pageContext.setAttribute("debtz_amount", debt.getAmount());
              			pageContext.setAttribute("date", new SimpleDateFormat("MMM dd").format(debt.getDateCreated())); 
              			pageContext.setAttribute("num", num);
              	%>
           				<div class="panel panel-default">
				    		<div class="panel-heading" role="tab" id="heading${num}">
				      			<h4 class="panel-title">
				        			<a data-toggle="collapse" data-parent="#accordion" href="#collapse${num}" aria-expanded="true" aria-controls="collapse${num}">
	            							<%
	            						if(debt.getDatePaid() == null){
					              			if(debt.getOwner().equals(userEmail)){					              				          			
		            							%>						
					          					<div>
					          						${fn:escapeXml(date)} | ${fn:escapeXml(debtz_name)}: 
					          						<span style="color: green">${fn:escapeXml(debtz_amount)}</span>
					          						<span class="glyphicon glyphicon-plus" style="float: right"></span>
				          						</div>
					          			<% 
					              			} else {
			              				%>						
					          					<div>
					          						${fn:escapeXml(date)} | ${fn:escapeXml(debtz_name)}: 
					          						<span style="color: red">-${fn:escapeXml(debtz_amount)}</span>
					          						<span class="glyphicon glyphicon-plus" style="float: right"></span>
				          						</div>
					          			<% 		
					              			}
	            						} else {
	            							if(debt.getOwner().equals(userEmail)){					              				          			
		            							%>						
					          					<div>
					          						${fn:escapeXml(date)} | ${fn:escapeXml(debtz_name)}: 
					          						<span style="color: green; text-decoration: line-through;">${fn:escapeXml(debtz_amount)}</span>
					          						<span style="font-style: italic">PAID</span>
					          						<span class="glyphicon glyphicon-plus" style="float: right"></span>
				          						</div>
					          			<% 
					              			} else {
			              				%>						
					          					<div>
					          						${fn:escapeXml(date)} | ${fn:escapeXml(debtz_name)}: 
					          						<span style="color: red; text-decoration: line-through;">-${fn:escapeXml(debtz_amount)}</span>
					          						<span style="font-style: italic">PAID</span>
					          						<span class="glyphicon glyphicon-plus" style="float: right"></span>
				          						</div>
					          			<%
					              			}
	            						}
					          			%>
				        			</a>
				      			</h4>
				    		</div>
					    	<div id="collapse${num}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${num}">
					      		<div class="panel-body">
					      			<button type="button" class="btn btn-primary">
										<a href="/home" style="color: white"> Mark as Paid </a>
									</button>
									<button type="button" class="btn btn-primary">
										Edit
									</button>
					    		</div>
					  		</div>		 
						</div>
              	<%
              			num++;
			  		}
              	%>     
				</div>
			</div>
		</div>
	</body>
</html>