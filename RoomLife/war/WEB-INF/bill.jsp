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
	    
	 
	    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="../../RoomLife.css">

	    
		<script src="../../Bootstrap/js/bootstrap.js"></script>
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
	  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	  	<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  		<script>
  			$(function() {
    			$( "#datepicker" ).datepicker();
  			});
	  	</script>
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="sidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <h1>Submit Bills and Debts</h1>
                <%
                	String errorMessage = (String) request.getAttribute("error");
                	if (errorMessage != null){
                		pageContext.setAttribute("error_message", errorMessage);
               		%>
	               		<div class="alert alert-danger" role="alert">
						  	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						  	<span class="sr-only">Error:</span>
						  	${fn:escapeXml(error_message)}
						</div>
               		<%		               		
                	}
                	if (request.getAttribute("edit_bill") == null){
               		%>
        			    <form action="BillEntry" method="POST">
        			    	<fieldset>
        			    		<legend>New Bill (split evenly among all roommates)</legend>
        			    		
        			    		<label class="field" for="Name">Bill Name:</label>
        				        <p><input type="text" name="billName"></p>
        				        <label class="field" for="Amount">Amount:</label>
        				        <p><input type="text" name="billAmount"></p>
        				        <label class="field" for="Amount">Due on:</label>
        					    <p><input type="text" name="billDate" id="datepicker"></p>
        					    <input hidden="true" type="text" name="edit" value="false">
        				        <input type="submit" name="submitBill" value="Submit">
        			        </fieldset>
        			    </form>
       			    <%
                	} else {
                		Bill bill = (Bill) request.getAttribute("edit_bill");
                		pageContext.setAttribute("billName", bill.getName());
                		pageContext.setAttribute("billAmount", bill.getAmount());
                		pageContext.setAttribute("billId", bill.getId());
                		pageContext.setAttribute("billDate", new SimpleDateFormat("MM/dd/yyyy").format(bill.getDateDeadline()));
					%>
        			    <form action="/entry/BillEntry" method="POST">
        			    	<fieldset>
        			    		<legend>Edit Bill (split evenly among all roommates)</legend>
        			    		
        			    		<label class="field" for="Name">Bill Name:</label>
        				        <p><input type="text" name="billName" value="${billName}"></p>
        				        <label class="field" for="Amount">Amount:</label>
        				        <p><input type="text" name="billAmount" value="${billAmount}"></p>
        				        <label class="field" for="Amount">Due on:</label>
        					    <p><input type="text" name="billDate" id="datepicker" value=${billDate}></p>
        					    <input hidden="true" type="text" name="edit" value="${billId}">
        				        <input type="submit" name="submitBill" value="Submit">
        			        </fieldset>
        			    </form>
       			    <%
                	}
                %>
			    
			    <br>
			    <br>
			    
			    <%		               		
                	
               	if (request.getAttribute("edit_debt") == null){
          		%>
				    <form action="DebtEntry" method= "POST">
				    	<fieldset>
				    		<legend>New Debt (charge a single roommate)</legend>
				    		
					    	<label class="field" for="Name">Debt Name:</label>
						    <p><input type="text" name="debtName"></p>
						    <label class="field" for="RoommateName">Roommate:</label>
						    <p><select name="roommate">
						    	<%
						    		List<Person> roommates = (List<Person>) request.getAttribute("roommateNames");
						    		for (Person mate: roommates){
						    			pageContext.setAttribute("roommate_name", mate.getName());
						    			pageContext.setAttribute("roommate_email", mate.getEmail());
						    		%>
						    			<option value="${roommate_email}">${fn:escapeXml(roommate_name)}</option>					    			
						    		<%	
						    		}					    	
						    	%>
						    </select></p>
						    <label class="field" for="Amount">Amount:</label>
						    <p><input type="text" name="debtAmount"></p>
						    <input hidden="true" type="text" name="edit" value="false">
					        <input type="submit" name="submitDebt" value="Submit">
				        </fieldset>
				    </form>
			    <%
               	} else {
               		Debt debt = (Debt) request.getAttribute("edit_debt");
               		pageContext.setAttribute("debtName", debt.getName());
               		pageContext.setAttribute("debtAmount", debt.getAmount());
               		pageContext.setAttribute("debtId", debt.getId());
				%>
					<form action="/entry/DebtEntry" method= "POST">
				    	<fieldset>
				    		<legend>New Debt (charge a single roommate)</legend>
				    		
					    	<label class="field" for="Name">Debt Name:</label>
						    <p><input type="text" name="debtName" value="${debtName}"></p>
						    <label class="field" for="RoommateName">Roommate:</label>
						    <p><select name="roommate">
						    	<%
						    		List<Person> roommates = (List<Person>) request.getAttribute("roommateNames");
						    		for (Person mate: roommates){
						    			pageContext.setAttribute("roommate_name", mate.getName());
						    			pageContext.setAttribute("roommate_email", mate.getEmail());
						    			if (mate.getEmail().equals(debt.getDebtor())){
						    				%>
							    				<option selected="selected" value="${roommate_email}">${fn:escapeXml(roommate_name)}</option>					    			
							    			<%	
						    			} else {
							    		%>
							    			<option value="${roommate_email}">${fn:escapeXml(roommate_name)}</option>					    			
							    		<%	
						    			}
						    		}					    	
						    	%>
						    </select></p>
						    <label class="field" for="Amount">Amount:</label>
						    <p><input type="text" name="debtAmount" value="${debtAmount}"></p>
						    <input hidden="true" type="text" name="edit" value="${debtId}">
					        <input type="submit" name="submitDebt" value="Submit">
				        </fieldset>
				    </form>
			    <%
               	}
            %>
			</div>
		</div>
		</div>
	</body>
</html>
