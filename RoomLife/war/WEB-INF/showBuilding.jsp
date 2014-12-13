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
	    <script src="http://code.jquery.com/jquery.js"></script>
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
							<h1>${fn:escapeXml(name)}: ${fn:escapeXml(address)}</h1>
							<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
								<div class="panel panel-default">
						    		<div class="panel-heading" role="tab" id="headingOne">
						      			<h3 class="panel-title">
						      				<a class data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
												<div>
													<span>People in apartment</span>
													<span class="glyphicon glyphicon-plus" style="float: right"></span>
												</div>
											</a>
										</h3>
									</div>
									<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseOne">
							      		<ul class="list-group">					
									<%
										if(emails!=null){
											for(String email:emails){
												Person person=datastore.getPerson(email);
												String name=person.getName();
												pageContext.setAttribute("personName", name);
											%>
												<li class="list-group-item">${fn:escapeXml(personName)}</li>
											<%
											}
										}
									%>
										</ul>
						    		</div>
					    		</div>
				    		</div>													
								
							<div>Maintenance Requests</div>
						<%			
						if(mainReq!=null){
							for(Long mKey:mainReq){
								MaintenanceRequest man = datastor.getMaintenanceRequest(mKey);
								if(man!=null){
									pageContext.setAttribute("id", mKey);
									pageContext.setAttribute("req_name", man.getName());
									pageContext.setAttribute("Priority", man.getPriority() );
									pageContext.setAttribute("Date", man.getDateCreated());
									pageContext.setAttribute("Details",man.getDetails());
									pageContext.setAttribute("Location", man.getLocation());																														
								%>
										<ul>${fn:escapeXml(req_name)}</ul>
										<ul>
									
								<% 
									if(man.getCompleted()==null){
									%>
										<ul><a href="/landlorddetailservlet/mainreq/${id}">Not been completed</a></ul>
									<%
									} else{
				                    	pageContext.setAttribute("req_complete", man.getCompleted());
			                    	%>
		                    			<ul>Completed on ${fn:escapeXml(req_complete)}</ul>
		                    		<%
									}
								%>	
									<ul>${fn:escapeXml(Priority)}</ul>
									<ul>${fn:escapeXml(Date)}</ul>
									<ul>${fn:escapeXml(Location)}</ul>
									<ul>${fn:escapeXml(Details)}</ul>
									</ul>	
								<%
								}
							}
						}
                	}
                }
			%>
			</div>
		</div>
		</div>
	</body>
</html>