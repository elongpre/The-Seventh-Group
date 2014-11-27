package com.appspot.t_gecko_764;

import static com.googlecode.objectify.ObjectifyService.ofy;

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
    	
    	
	    // get the name and total amount of the debt, as well as the associated roommate
	    String name = req.getParameter("name");
    	String priority = req.getParameter("priority");
	    String place = req.getParameter("place");
	    String description = req.getParameter("description");
	    String groupName = req.getParameter("group");
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Person owner= datastore.getPerson(user.getEmail());
	    Group group= datastore.getGroup(owner, groupName);

	    
	    // construct new Debt object
	    MaintenanceRequest mainreq = new MaintenanceRequest.Builder(groupName, owner, group).setName(name).setPriority(priority).setLocation(place)
	    		.setDetails(description).build();
	    
	    group.addMaintenanceRequest(mainreq);
	    datastore.saveGroup(group);
	    // push new maintenancerequest to the datastore
	    ofy().save().entity(owner).now();
    }
    

    
}
