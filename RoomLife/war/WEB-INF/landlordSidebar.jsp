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
			                		Buildings
			               		</div>
                			</td>
                			<td align="right" >
                				<a href="/landlordentry/building" style="padding-right: 10px">
               						<span class="glyphicon glyphicon-plus"></span>
               					</a>            				
                			</td>
                		</tr>
                	</table>                  	
                </li>
                <li class="divider"></li>
                <%
					List<Building> buildings = (List<Building>) request.getAttribute("Buildings");
					DataStore datastore=DataStore.getInstance();

                	if (buildings!=null){
                		int i = 0;
						for (Building building : buildings){
							pageContext.setAttribute("building_name", building.getName());
							pageContext.setAttribute("building_id", building.getId());
							if(building.getGroups()!=null){
								pageContext.setAttribute("numGroups",building.getGroups().size());
							}
							else{
								pageContext.setAttribute("numGroups", 0);
							}
				%>
						<li>
							<a href="/landlorddetailservlet/building/${building_id}" class="building-name">&nbsp;&nbsp;&nbsp;${fn:escapeXml(building_name)}: ${fn:escapeXml(numGroups)}</a>
						</li>
				<%
							i++;
									if(i == 5){
										break;
									}
						}
							   
							
						
                		}
				%>                         
            </ul>
        </div>
	</body>
</html>