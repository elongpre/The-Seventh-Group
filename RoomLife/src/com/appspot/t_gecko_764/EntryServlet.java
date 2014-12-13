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

public class EntryServlet extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
			UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    
		    if( user == null){
		    	resp.sendRedirect("/LoginPage.jsp");
		    }
		    
		    String path = req.getRequestURL().toString();
			String[] splitPath = path.split("/");
			int length = splitPath.length;
			if( splitPath[length-1].contains("css")){
				return;
			}
			
			DataStore datastore = DataStore.getInstance();
			Person person = datastore.getPerson(user.getEmail());

			String userEmail = person.getEmail();
			
			ServletHelper.initializeServlet(req, resp, person);
						
			
			ArrayList<Person> names= new ArrayList<Person>();
			for(String email : datastore.getGroup(person.getGroup()).getMembers()){
				names.add(datastore.getPerson(email));
			}
			req.setAttribute("roommateNames", names);

			switch (splitPath[length-1]) {
				case "bill": if(!splitPath[length-2].equals("entry")){
						req.setAttribute("edit_bill", datastore.getBill(Long.parseLong(splitPath[length-2], 10)));
					}
					req.getRequestDispatcher("/WEB-INF/bill.jsp").forward(req, resp);
					break;
				case "debt": if(!splitPath[length-2].equals("entry")){
						req.setAttribute("edit_debt", datastore.getDebt(Long.parseLong(splitPath[length-2], 10)));
					}
					req.getRequestDispatcher("/WEB-INF/bill.jsp").forward(req, resp);
					break;
				case "request": if(!splitPath[length-2].equals("entry")){
						req.setAttribute("edit_req", datastore.getMaintenanceRequest(Long.parseLong(splitPath[length-2], 10)));
					}
					req.getRequestDispatcher("/WEB-INF/mainrequest.jsp").forward(req, resp);
					break;
			}
		}
		
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
			this.doGet(req, resp);
		}
}
