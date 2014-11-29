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

@SuppressWarnings("serial")
public class DebtServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
    	// establish the creation date for the current debt
    	//Date date = new Date();
    	//long date_created = date.getTime();
    	
    	// obtain current user
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get the name and total amount of the debt, as well as the associated roommate
	    String debt_name = req.getParameter("debtName");
	    Double amount = Double.parseDouble(req.getParameter("debtAmount"));
	    String roommate = req.getParameter("roommate");
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Person owner = datastore.getPerson(user.getEmail());
	    
	    //get debtor(roommate) from the datastore
	    String debtor_email = findRoommate(owner, roommate);
	    Person debtor = datastore.getPerson(debtor_email);
	    
	    // construct new Debt object
	    Debt debt = new Debt.Builder(debt_name, amount, owner, debtor).build();
	    
	    // push new debt to the datastore
	    datastore.saveDebt(debt);
    }
    
    private String findRoommate(Person owner, String name) {
    	ArrayList<Person> roommates = new ArrayList<Person>();
    	DataStore datastore = DataStore.getInstance();
    	for(String email: owner.getRoommates()){
    		Person person = datastore.getPerson(email);
    		if( person.getName() == name){
    			return email;
    		}
    	}
    	return null;
    }
    
}
	    