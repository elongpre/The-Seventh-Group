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
		    
			DataStore datastore = DataStore.getInstance();
			Person person = datastore.getPerson(user.getEmail());

			String userEmail = person.getEmail();
			
			ServletHelper.initializeServlet(req, resp, person);
			
			String path = req.getRequestURL().toString();
			String[] splitPath = path.split("/");
			int length = splitPath.length;
			if( splitPath[length-1].contains("css")){
				return;
			}
			switch (splitPath[length-1]) {
				case "bill": req.getRequestDispatcher("/WEB-INF/bill.jsp").forward(req, resp);
					break;
				case "debt": req.getRequestDispatcher("/WEB-INF/bill.jsp").forward(req, resp);
					break;
				case "request": req.getRequestDispatcher("/WEB-INF/mainrequest.jsp").forward(req, resp);
					break;
			}
		}
}
