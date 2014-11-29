package com.appspot.t_gecko_764;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class RegisterUserServlet extends HttpServlet{
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
	    // get parameters from post
	    String name = req.getParameter("firstname") + req.getParameter("secondname");
	    String groupName = req.getParameter("group");
	    String email = req.getParameter("email");
	    
	    // create person and group
	    Person person = new Person(name,email);
	    Group group = new Group(groupName,person);
	    person.addGroup(group);
	    
	    // add person and group to database
	    DataStore datastore = DataStore.getInstance();
	    datastore.saveGroup(group);
	    datastore.savePerson(person);
	    
	    resp.sendRedirect("/LoginPage.jsp");

    }

}
