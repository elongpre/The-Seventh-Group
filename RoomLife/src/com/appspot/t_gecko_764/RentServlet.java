package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class RentServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Landlord owner = datastore.getLandlord(user.getEmail());
	    
		String path = req.getRequestURL().toString();
		String[] splitPath = path.split("/");
		int length = splitPath.length;
		if( splitPath[length-1].contains("css")){
			return;
		}
	    
	    Long id;
		
		id = Long.parseLong(splitPath[length-1], 10);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/WEB-INF/rent.jsp").forward(req, resp);

	}

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	
    	// obtain current user so the correct data can be pulled from the datastore
	    UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    
	    // get owner(current user) from the datastore
	    DataStore datastore = DataStore.getInstance();
	    Landlord owner = datastore.getLandlord(user.getEmail());
	    
		String path = req.getRequestURL().toString();
		String[] splitPath = path.split("/");
		int length = splitPath.length;
		if( splitPath[length-1].contains("css")){
			return;
		}
		Long id;
	    
	    switch(splitPath[length-2]){
	    	case "rent":
	    		id = Long.parseLong(splitPath[length-1], 10);
	    	    
	    	    String amount = req.getParameter("billAmount");
	    	    if(amount==null || amount.matches("0")){
	    	    	resp.sendRedirect("/home");
	    	    } else if(!amount.matches("[0-9]+") || !amount.matches("[0-9]*.[0-9]*") ){
	    	    	resp.sendRedirect("/home");
	    	    } else {
	    	    

	    	    	Double total_amount= Double.parseDouble(req.getParameter("billAmount"));
	    		    
	    		    // calculate amount for each roommate
	    		    Group group = datastore.getGroup(id);
	    		    for(String email : group.getMembers()){
	    		    	Person person = datastore.getPerson(email);
		    		    String date = req.getParameter("billDate");
		    		    String[] splitDate = date.split("/");
		    		    Calendar calendar = new GregorianCalendar();
		    		    calendar.set(Calendar.YEAR, new Integer(splitDate[2]));
		    		    calendar.set(Calendar.MONTH, new Integer(splitDate[0]) - 1);
		    		    calendar.set(Calendar.DAY_OF_MONTH, new Integer(splitDate[1]));	    
		    		    Date deadline = calendar.getTime();
		    		    Group basic = datastore.getBasicGroup();
		        
		    		    Bill bill = new Bill.Builder("Rent", total_amount, person).setGroup(basic).setDateDeadline(deadline).build();
		    		    // save bill to the Datastore
		    		    datastore.saveBill(bill);	 
	    		    }
	    	    	    
	    		    resp.sendRedirect("/home");
	    	    }
	        
	    	
	    		break;
	    }

	
    }
}
