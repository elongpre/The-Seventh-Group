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
                pageContext.setAttribute("bill_id", bill.getId());
                %>
              	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
              		<h1>${fn:escapeXml(bill_name)}<span style="float:right">Bill</span></h1>
              		<div class="panel panel-default">
			    		<div class="panel-heading" role="tab" id="headingFirst">
			      			<h3 class="panel-title">
			        			<a data-toggle="collapse" data-parent="#accordion" href="#collapseFirst" aria-expanded="true" aria-controls="collapseFirst">
			              	<% 
			              		if(bill.getDatePaid() == null) {
			              			pageContext.setAttribute("bill_deadline", new SimpleDateFormat("MMM dd, YYYY").format(bill.getDateDeadline()));
			              	%>              		
			              			<div style="font-size: 120%">
				              			<span style="color: red">$${fn:escapeXml(bill_amount)}</span> due on ${fn:escapeXml(bill_deadline)}
				              			<span class="glyphicon glyphicon-plus" style="float: right"></span>
			              			</div>
			              	<%
			              		} else {
			                    	pageContext.setAttribute("bill_paid", new SimpleDateFormat("MMM dd, YYYY").format(bill.getDatePaid()));
			                %>
			          				<div style="font-size: 120%">
				                		${fn:escapeXml(bill_name)} was paid on ${fn:escapeXml(bill_paid)}
				                		<span class="glyphicon glyphicon-plus" style="float: right"></span>
			                		</div>
			                <%
								}
			              	%>
			              		</a>
		              		</h3>
	              		</div>
	              		<div id="collapseFirst" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFirst">
				      		<div class="panel-body">
				      			<button type="button" class="btn btn-primary">
									<a href="/payservlet/bill/${bill_id}" style="color: white"> Mark as Paid </a>
								</button>
								<button type="button" class="btn btn-primary">
									Edit
								</button>
				    		</div>
			    		</div>
			  		</div>
		  			
	  		
              		<h3>Roommates Splitting the Bill</h3>
              	

           		<% 
           			ArrayList<String> names = (ArrayList<String>) request.getAttribute("names");
           			ArrayList<Double> amount = (ArrayList<Double>) request.getAttribute("amount");
           			Double payment = (Double) request.getAttribute("payment");
           			pageContext.setAttribute("payment", payment);
           			for (int j = 0; j < names.size(); j++){
           				pageContext.setAttribute("num", j);
           				pageContext.setAttribute("name", names.get(j));
           			%>
           				<div class="panel panel-default">
				    		<div class="panel-heading" role="tab" id="heading${num}">
				      			<h4 class="panel-title">
				        			<a data-toggle="collapse" data-parent="#accordion" href="#collapse${num}" aria-expanded="true" aria-controls="collapse${num}">
           								<div>
           									${fn:escapeXml(name)}: 
           									<%
           									if(amount.get(j) == 0){
           									%>
												<span style="text-decoration:line-through">
													<span style="color:green">$${fn:escapeXml(payment)}</span>
												</span>
												<span style="font-style: italic">PAID</span>           										
           									<%	
           									} else {
           									%>
												<span style="color:green">$${fn:escapeXml(payment)}</span>

           									<%	
           									}
           									%>
           									<span class="glyphicon glyphicon-plus" style="float: right"></span>
           								</div>
          							</a>
			              		</h4>
		              		</div>
		              		<div id="collapse${num}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${num}">
					      		<div class="panel-body">
					      			<button type="button" class="btn btn-primary">
										<a href="/payservlet/${name}/bill/${bill_id}" style="color: white"> Mark as Paid </a>
									</button>									
					    		</div>
				    		</div>
				  		</div>
           		<%			
           			}
           		%>
		  		</div>              		             	
			</div>
		</div>
		</div>
	</body>
</html>