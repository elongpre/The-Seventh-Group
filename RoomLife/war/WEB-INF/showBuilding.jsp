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
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="landlordSidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
                <% 
                DataStore datastor = DataStore.getInstance();
                Building building = (Building) request.getAttribute("building"); 
                String buildingName=building.getName();
                pageContext.setAttribute("buildingname", buildingName);
                List<Long> groupsKeys=building.getGroups();
                List<Group> groups= new ArrayList<Group>();
                List<String> emails= new ArrayList<String>();
                List <Long> mainReq = new ArrayList<Long>();
                %>
                <ul>
                <%
                
                if(groupsKeys!=null){
                	int i = 0;
                	for(Long key:groupsKeys){
                		Group group=datastor.getGroup(key);
                		pageContext.setAttribute("address",group.getAddress());
                		pageContext.setAttribute("name", group.getName());
                		pageContext.setAttribute("id", group.getId());
                		emails=group.getMembers();
                		mainReq=group.getMainreq();
                		
                		
            	%>
						
							<li>${fn:escapeXml(name)}: ${fn:escapeXml(address)}</li>
						
				<%
						if(emails!=null){
						for(String email:emails){
							Person person=datastore.getPerson(email);
							String name=person.getName();
							pageContext.setAttribute("personName", name);
							
				%>
							<ul>${fn:escapeXml(personName)}</ul>
				<% 

						}
						}
				
							i++;
									if(i == 5){
										break;
									}
								%>
								<br>
								<h1>Maintenance Request</h1>
								<br>
								<%
									
						if(mainReq!=null){
							%>
							Hi 
							<%

							for(Long mKey:mainReq){
								MaintenanceRequest man = datastor.getMaintenanceRequest(mKey);
								if(man!=null){
									pageContext.setAttribute("id", mKey);
									pageContext.setAttribute("req_name", man.getName());
									pageContext.setAttribute("Priority", man.getPriority() );
									pageContext.setAttribute("Date", man.getDateCreated());
									pageContext.setAttribute("Details",man.getDetails());
									pageContext.setAttribute("Location", man.getLocation());


								
									
									if(man.getCompleted()==null){
										%>
										<div>${fn:escapeXml(req_name)} has not been completed</div>
										<%
									}
									else{
				                    pageContext.setAttribute("req_complete", man.getCompleted());
				                    %>
			                    		<div>${fn:escapeXml(req_name)} was completed on ${fn:escapeXml(req_complete)}</div>
			                    	<%
									}
									%>
									<div>${fn:escapeXml(Date)} </div>
									<%
								}
							}
						}
							
                	}
                }
                


                %>
				</ul>
            	
			</div>
		</div>
		</div>
	</body>
</html>