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
	                pageContext.setAttribute("debtor", debtor); 
	                int num = 0;
                %>
                	<h1>${fn:escapeXml(debtor)}</h1> 
			  	<% 
			  		for(Debt debt: debtList){
              			pageContext.setAttribute("debtz_name", debt.getName());
              			pageContext.setAttribute("debtz_amount", debt.getAmount());
              			pageContext.setAttribute("num", num);
              	%>
           				<div class="panel panel-default">
				    		<div class="panel-heading" role="tab" id="heading${num}">
				      			<h4 class="panel-title">
				        			<a data-toggle="collapse" data-parent="#accordion" href="#collapse${num}" aria-expanded="true" aria-controls="collapse${num}">
	            							<%
				              			if(debt.getOwner().equals(userEmail)){					              				          			
	            							%>						
				          					<div>${fn:escapeXml(debtz_name)}: <span style="color: green">${fn:escapeXml(debtz_amount)}</span></div>
				          			<% 
				              			} else {
		              				%>						
				          					<div>${fn:escapeXml(debtz_name)}: <span style="color: red">-${fn:escapeXml(debtz_amount)}</span></div>
				          			<% 		
				              			}
				          			%>
				        			</a>
				      			</h4>
				    		</div>
					    	<div id="collapse${num}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading${num}">
					      		<div class="panel-body">
					        		Debt Details Will Go Here
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