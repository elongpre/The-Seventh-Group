package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class HomeServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    if( user == null){
	    	resp.sendRedirect("/LoginPage.jsp");
	    }
	    
	    // obtain the datastore access object
		DataStore datastore = DataStore.getInstance();
		
		// get current user's email
		String emailString = user.getEmail();
		
		// check the datastore for a landlord with the current email address
		Landlord landlord = datastore.getLandlord(emailString);
		
		// check datastore for person with current email
		Person person = datastore.getPerson(emailString);
		
		// user is a landlord
		if (landlord != null) {
			List<Long> buildingsKey = landlord.getBuildings();
			ArrayList<Building> buildings=new ArrayList<Building>();
			
			if(buildingsKey != null){
				for(Long buildingKey : buildingsKey){
					buildings.add(datastore.getBuilding(buildingKey));
				}
			}
			req.setAttribute("Buildings", buildings);
			req.getRequestDispatcher("/WEB-INF/landlordHome.jsp").forward(req, resp);
		} 
		
		// user is a tenant
		else if (person != null) {	
			req.setAttribute("UserName", person.getName());
			
			//check for recent bills
			ServletHelper.initializeServlet(req, resp, person);			
			req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
		} 
		
		// user does not exist
		else {
			resp.sendRedirect("/LoginPage.jsp");
		}
	}
	
}
