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
	    
	    DataStore datastore = DataStore.getInstance();
	    
	    if (datastore.getPerson(email)==null){
		    Person person = new Person(name,email);
		    Group group = new Group(groupName);
		    datastore.saveGroup(group);
		    group.addMember(person);
		    datastore.saveGroup(group);
		    person.setGroup(group.getId());
		    datastore.savePerson(person);
		    resp.sendRedirect("/LoginPage.jsp");
	    }else{
	    	resp.sendRedirect("/registerUser.jsp?failed=yes");
	    }
	    

	    
	  

    }

}
