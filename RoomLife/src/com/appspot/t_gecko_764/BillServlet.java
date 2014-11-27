package com.appspot.t_gecko_764;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Result;

public class BillServlet extends HttpServlet{
	DataStore datastore = DataStore.getInstance();

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
    	// obtain current user so the correct data can be pulled from the datastore
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Person owner = datastore.getPerson(user.getEmail());
	    
	    // get the name of the bill and the amount
	    String bill_name = req.getParameter("billName");
	    Double total_amount = Double.parseDouble(req.getParameter("billAmount"));
	    
	    // calculate amount for each roommate
	    Double split_amount = total_amount/(owner.getRoommates().size());
	    
	    // create bill and update all those affected, which should be the current user and his/her roommates
	    Bill bill = new Bill(bill_name, split_amount, owner);
	    distributeBill(bill, owner);

    }
    
	public void distributeBill(Bill bill, Person owner) {
		ArrayList<Person> roommates = owner.getRoommates();
		
		for(Person roommate : roommates) {
			Person p = datastore.getPersonviaId(roommate);
			p.addBill(bill);
			datastore.savePerson(p);;
		}
	}
	

}
