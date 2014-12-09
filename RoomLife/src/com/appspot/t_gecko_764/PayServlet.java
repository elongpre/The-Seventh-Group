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

public class PayServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    if( user == null){
	    	resp.sendRedirect("/LoginPage.jsp");
	    }
	    
		DataStore datastore = DataStore.getInstance();
		Person person = datastore.getPerson("alice@example.com");
		String userEmail = person.getEmail();		
		
		List<String> emails = datastore.getGroup(person.getGroup()).getMembers();
		emails.remove(person.getEmail());
		ArrayList<String> roommates = new ArrayList<String>();
		for(String email : emails){
			roommates.add(datastore.getPerson(email).getName());
		}

		String path = req.getRequestURL().toString();
		String[] splitPath = path.split("/");
		int length = splitPath.length;
		if( splitPath[length-1].contains("css")){
			return;
		}
		Long id;
		switch (splitPath[length-2]) {
			case "bill":	id = Long.parseLong(splitPath[length-1], 10);
				Bill bill = datastore.getBill(id);
				if (splitPath[length-3].equals("payservlet")){
					bill.setDatePaid(new Date());					
				} else {
					String name = splitPath[length-3];
					Person debtor = null;
					for(String email : emails){
						debtor = datastore.getPerson(email);
						if(debtor.getName().equalsIgnoreCase(name)){
							break;
						}
					}
					bill.getPeeps().remove(debtor.getEmail());
				}
				datastore.saveBill(bill);
				resp.sendRedirect("/detailservlet/bill/" + id.toString());
				break;
			case "debt":	id = Long.parseLong(splitPath[length-1], 10);
				Debt debt = datastore.getDebt(id);
				debt.setDatePaid(new Date());
				datastore.saveDebt(debt);
				String name = datastore.getPerson(debt.getDebtor()).getName();
				req.getRequestDispatcher("/detailservlet/debt/" +  name).forward(req, resp);
				break;
			default:		resp.sendRedirect("/home");
				break;
		}
	}
}
