package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class DetailServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		UserService userService = UserServiceFactory.getUserService();
	    User user = userService.getCurrentUser();
	    if( user == null){
	    	resp.sendRedirect("/LoginPage.jsp");
	    }
	    
		DataStore datastore = DataStore.getInstance();
		Person person = datastore.getPerson("alice@example.com");
		List<Bill> bills = datastore.getBills(person);
		req.setAttribute("BillList", bills);
		List<Debt> debts = datastore.getDebts(person);
		req.setAttribute("DebtList", debts);
		List<MaintenanceRequest> requests = datastore.getMaintenanceRequests(person);
		req.setAttribute("RequestList", requests);

		String path = req.getRequestURL().toString();
		String[] splitPath = path.split("/");
		int length = splitPath.length;
		if( splitPath[length-1].contains("css")){
			return;
		}
		Long id = Long.parseLong(splitPath[length-1], 10);
		switch (splitPath[length-2]) {
			case "bill":	Bill bill = datastore.getBill(id);
				req.setAttribute("Bill", bill);
				Group group = datastore.getGroup(person.getGroup());
				ArrayList<String> names = new ArrayList<String>();
				ArrayList<Double> amount = new ArrayList<Double>();
				double payment = bill.getAmount()/group.getMembers().size();
				payment = Math.round( payment * 100.0 ) / 100.0;
				for(String email: group.getMembers()){
					Person payer = datastore.getPerson(email);
					names.add(payer.getName());
					if(bill.getPeeps().contains(email)){
						amount.add(payment);
					} else {
						amount.add(0.0);
					}
				}
				req.setAttribute("names", names);
				req.setAttribute("amount", amount);
				req.getRequestDispatcher("/WEB-INF/showBill.jsp").forward(req, resp);
				break;
			case "debt":	Debt debt = datastore.getDebt(id);
				req.setAttribute("Debt", debt);
				req.setAttribute("debtor", debt.getDebtor());
				req.getRequestDispatcher("/WEB-INF/showDebt.jsp").forward(req, resp);
				break;
			case "request":	MaintenanceRequest request = datastore.getMaintenanceRequest(id);
				req.setAttribute("Request", request);
				req.getRequestDispatcher("/WEB-INF/showRequest.jsp").forward(req, resp);
				break;
			default:		resp.sendRedirect("/home");
				break;
		}
	}
}
