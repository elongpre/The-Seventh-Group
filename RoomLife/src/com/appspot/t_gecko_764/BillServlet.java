package com.appspot.t_gecko_764;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Objectify;

public class BillServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		Person x = ofy().load().type(Person.class).filter("name","Guillermo Nunez").first().now();
		String name= x.getName();
		resp.sendRedirect("/TaskComplete.jsp?name="+name);
		
		
	}
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();

    // We have one entity group per Guestbook with all Greetings residing
    // in the same entity group as the Guestbook to which they belong.
    // This lets us run a transactional ancestor query to retrieve all
    // Greetings for a given Guestbook.  However, the write rate to each
    // Guestbook should be limited to ~1/second.
    //String guestbookName = req.getParameter("guestbookName");
    
    //ofy().save().entity(person).now();

    resp.sendRedirect("/TaskComplete.jsp?name=");
}
	
	

}
