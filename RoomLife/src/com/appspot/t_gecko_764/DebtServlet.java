package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class DebtServlet extends HttpServlet {
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
    	
    	//Date date = new Date();
    	//long date_created = date.getTime();
    	
    	// obtain current user
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get the name and total amount of the debt, as well as the associated roommate
	    String debt_name = req.getParameter("debtName");
	    String roommate = req.getParameter("roommate");
	    String debtAmount=req.getParameter("debtAmount");
	    
	    if(debtAmount==null || debtAmount.matches("0")){
	    	req.setAttribute("error", "There must be a debt amount");
	    	req.getRequestDispatcher("/entry/debt").forward(req, resp);
	    } else if(!debtAmount.matches("[0-9]+") || !debtAmount.matches("[0-9]*.[0-9]*") ){
	    	req.setAttribute("error", "Debt amount was not a number");
	    	req.getRequestDispatcher("/entry/debt").forward(req, resp);
	    } else {
	    
		    Double amount = Double.parseDouble(req.getParameter("debtAmount"));
		    
		    // get owner(current user) from the datastore
		    DataStore datastore = DataStore.getInstance();
		    Person owner = datastore.getPerson(user.getEmail());
		    
		    
		    //get debtor(roommate) from the datastore
		    //String debtor_email = findRoommate(owner, roommate);
		    Person debtor = datastore.getPerson(roommate);
		    
		    // construct new Debt object
		    Debt debt = new Debt.Builder(debt_name, amount, owner, debtor).build();
		    
		    // push new debt to the datastore
		    datastore.saveDebt(debt);
		    
		    resp.sendRedirect("/detailservlet/debt/" + debtor.getName());
	    }
	    
    }
    
    private String findRoommate(Person owner, String name) {
    	DataStore datastore = DataStore.getInstance();
    	List<String> roommates = datastore.getGroup(owner.getGroup()).getMembers();
    	for(String email: roommates){
    		Person person = datastore.getPerson(email);
    		if( person.getName() == name){
    			return email;
    		}
    	}
    	return null;
    }
    
}
	    