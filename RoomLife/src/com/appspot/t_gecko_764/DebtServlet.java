package com.appspot.t_gecko_764;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Objectify;

@SuppressWarnings("serial")
public class DebtServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
    	// establish the creation date for the current debt
    	Date date = new Date();
    	long date_created = date.getTime();
    	
    	// obtain current user
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get the name and total amount of the debt, as well as the associated roommate
	    String debt_name = req.getParameter("debtName");
	    Double amount = Double.parseDouble(req.getParameter("debtAmount"));
	    String roommate = req.getParameter("roommate");
	    
	    // get owner(current user) from the datastore
	    Person owner = ofy().load().type(Person.class).filter("email", user.getEmail()).first().now();
	    
	    //get debtor(roommate) from the datastore
	    String debtor_email = findRoommate(owner, roommate);
	    Person debtor = ofy().load().type(Person.class).filter("email", debtor_email).first().now();
	    
	    // construct new Debt object
	    Debt debt = new Debt.Builder(debt_name, amount, owner, debtor).build();
	    
	    // push new debt to the datastore
	    ofy().save().entity(debt).now();
    }
    
    private String findRoommate(Person owner, String name) {
    	ArrayList<Person> roommates = owner.getRoommates();
    	String email = null;
    	
    	// search the list of roommates for the name pulled from the debt input page
    	for(Person roommate : roommates) {
    		if (roommate.getName() == name) {
    			email = roommate.getEmail();
    		}
    	}
    	return email;
    }
    
}
	    