package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LandLordDetailServlet extends HttpServlet{
		
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
			
			if(buildingsKey != null){
				for(Long buildingKey : buildingsKey){
					buildings.add(datastore.getBuilding(buildingKey));
				}
			}
			req.setAttribute("Buildings", buildings);


			String path = req.getRequestURL().toString();
			String[] splitPath = path.split("/");
			int length = splitPath.length;
			if( splitPath[length-1].contains("css")){
				return;
			}
			Long id;
			
			String parameter=splitPath[length-2];
			if(parameter.equals("building")){
				id = Long.parseLong(splitPath[length-1], 10);
				Building building = datastore.getBuilding(id);
				req.setAttribute("building", building);
				req.getRequestDispatcher("/WEB-INF/showBuilding.jsp").forward(req, resp);
			
			}else
				resp.sendRedirect("/home");
			}
			


		
					
			
		
	


}
