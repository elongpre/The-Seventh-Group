package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class BuildingServlet extends HttpServlet{

	    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	            throws IOException {
	    	
	    	// obtain current user so the correct data can be pulled from the datastore
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    
		    // get owner(current user) from the datastore
		    DataStore datastore = DataStore.getInstance();
		    Landlord owner = datastore.getLandlord(user.getEmail());
		    
		    if(owner==null){
		    	resp.sendRedirect("/LoginPage.jsp");
		    }
		    
		    String name=req.getParameter("buildingName");
		    Building building = new Building(name,user.getEmail());
		    datastore.saveBuilding(building);
		    owner.addBuilding(building.getId());
		    datastore.saveLandlord(owner);
		    
		    
			List<Long> buildingsKey = owner.getBuildings();
			ArrayList<Building> buildings=new ArrayList<Building>();
			if (owner!=null){
				if(buildingsKey != null){
					for(Long buildingKey : buildingsKey){
						buildings.add(datastore.getBuilding(buildingKey));
						
					}
				}
		   
		    req.setAttribute("Buildings", buildings);
		    resp.sendRedirect("/home");
		    
	    }
	}
}

