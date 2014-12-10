package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LandlordEntryServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    if( user == null){
	    	resp.sendRedirect("/LoginPage.jsp");
	    }
	    
	
	    
	    DataStore datastore = DataStore.getInstance();
	    Landlord landlord=datastore.getLandlord(user.getEmail());
	    
		List<Long> buildingsKey = landlord.getBuildings();
		ArrayList<Building> buildings=new ArrayList<Building>();
		if (landlord!=null){
			if(buildingsKey != null){
				for(Long buildingKey : buildingsKey){
					buildings.add(datastore.getBuilding(buildingKey));
				}
			}
		req.setAttribute("Buildings", buildings);
		
		}
	    
	    
		String path = req.getRequestURL().toString();
		String[] splitPath = path.split("/");
		System.out.println(splitPath);
		int length = splitPath.length;

		switch (splitPath[length-2]) {
			case "building": req.getRequestDispatcher("/WEB-INF/building.jsp").forward(req, resp);

		}
	}

}
