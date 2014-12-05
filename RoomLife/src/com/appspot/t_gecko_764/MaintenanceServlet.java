package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class MaintenanceServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	UserService userService = UserServiceFactory.getUserService();
    	User user = userService.getCurrentUser();
    	
	    // get parameters for maintenance request
	    String name = req.getParameter("name");
    	String priority = req.getParameter("priority");
	    String place = req.getParameter("place");
	    String description = req.getParameter("description");
	    String groupName = req.getParameter("group");
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Person owner = datastore.getPerson(user.getEmail());
	    Group group = datastore.getGroup(owner.getGroup());
	    
	    
	    // construct new maintenance request object using Builder
	    MaintenanceRequest mainreq = new MaintenanceRequest.Builder(groupName, owner, group).setName(name).setPriority(priority).setLocation(place)
	    		.setDetails(description).build();
	    
	    // add request to group and add group to datastore
	    group.addMaintenanceRequest(mainreq);
	    datastore.saveGroup(group);
	    
	    resp.sendRedirect("/TaskComplete.html");

    }
    
}
