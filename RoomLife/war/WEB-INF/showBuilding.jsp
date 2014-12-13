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
		
		<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Oswald|Titillium+Web:600' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="../../RoomLife.css">
	</head>
	<body>
		<%@include file="header.jsp" %> 
		<div id="wrapper">
		
      	<%@include file="landlordSidebar.jsp" %> 
        <!-- Page Content -->
        <div id="page-content-wrapper">
        	<div class="container-fluid">
        	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
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
                		pageContext.setAttribute("num", i);
                		emails=group.getMembers();
                		mainReq=group.getMainreq();               		                		
         					%>
						<div class="panel panel-default">
				    		<div class="panel-heading" role="tab" id="heading${num}">
				      			<h1 class="panel-title">
				      				<a class data-toggle="collapse" data-parent="#accordion" href="#collapse${num}" aria-expanded="true" aria-controls="collapse${num}">
										<div>
											<span>${fn:escapeXml(name)}: ${fn:escapeXml(address)}</span>																										
											<span class="glyphicon glyphicon-plus" style="float: right"></span>
										</div>
									</a>
								</h1>
							</div>
							<div id="collapse${num}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapse${num}">
					      		<ul class="list-group">
					      			<li class="list-group-item"><span>People in apartment:</span>
					      				<span>				
										<%
											if(emails!=null){
												for(String email:emails){
													Person person=datastore.getPerson(email);
													String name=person.getName();
													pageContext.setAttribute("personName", name);
												%>
													&nbsp;&nbsp;${fn:escapeXml(personName)}
												<%
												}
											}
										%>
										</span>
										<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/rent/${id}">Create rent reminder</a></div>	
									</li>												
									<li class="list-group-item">
										<h4>Maintenance Requests</h4>
										<%			
										if(mainReq!=null){
											for(Long mKey:mainReq){
												MaintenanceRequest man = datastor.getMaintenanceRequest(mKey);
												if(man!=null){
													pageContext.setAttribute("mkey", mKey);
													pageContext.setAttribute("req_name", man.getName());
													pageContext.setAttribute("Priority", man.getPriority() );
													pageContext.setAttribute("Date", new SimpleDateFormat("MMM dd, YYYY").format(man.getDateCreated()));
													pageContext.setAttribute("Details",man.getDetails());
													pageContext.setAttribute("Location", man.getLocation());																														
												%>
														<ul>${fn:escapeXml(req_name)}</ul>
														<ul>
													
												<% 
													if(man.getCompleted() == null){
													%>
														<ul><a href="/landlorddetailservlet/mainreq/${mkey}">Not been completed</a></ul>
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
										%>
									</li>
								</ul>
							</div>
						</div>	
						<%
               		i++;
                	}
                }
				%>
			</div>
			</div>
		</div>
		</div>
	</body>
</html>