package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class BillServlet extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
    	// obtain current user so the correct data can be pulled from the datastore
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Person owner = datastore.getPerson(user.getEmail());
	    
	    // get the name of the bill and the total amount
	    String bill_name = req.getParameter("billName");
	    Double total_amount = Double.parseDouble(req.getParameter("billAmount"));
	    
	    // calculate amount for each roommate
	    Group group = datastore.getGroup(owner.getGroup());
	    Double split_amount = total_amount /(group.getMembers().size());
	    System.out.println(split_amount.toString());
	    
	    // create bill 
	    Bill bill = new Bill.Builder(bill_name, split_amount, owner).setGroup(group).build();
	    // save bill to the Datastore
	    datastore.saveBill(bill);
	    
	   //  update all those affected, which should be the current user and his/her roommates
	    ArrayList<String> peeps=group.getMembers();
	    
		for(String member:peeps){
			if(!bill.getOwner().equals(member)){
			Person roommate=datastore.getPerson(member);
			if(roommate.getBills()==null)
			{
				ArrayList<Long> bills=new ArrayList<Long>();
				roommate.setBills(bills);

			}
			roommate.addBill(bill.getId());
			datastore.savePerson(roommate);

			}
		}

	    
	    resp.sendRedirect("/TaskComplete.html");
	    
    }
}
