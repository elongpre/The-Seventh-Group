package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
		String userEmail = person.getEmail();
		req.setAttribute("UserEmail", userEmail);
		
		ServletHelper.initializeServlet(req, resp, person);
		
		List<Bill> bills = datastore.getBills(person);
		List<String> emails = datastore.getGroup(person.getGroup()).getMembers();
		emails.remove(person.getEmail());
		ArrayList<String> roommates = new ArrayList<String>();
		for(String email : emails){
			roommates.add(datastore.getPerson(email).getName());
		}
		int numRoommates = roommates.size();
		ArrayList<Double> charges = new ArrayList<Double>();
		List<Debt> posDebts = datastore.getDebts(person);
		List<Debt> negDebts = datastore.getDebtsDebtor(person);

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
				req.setAttribute("Bill", bill);
				Group group = datastore.getGroup(person.getGroup());
				ArrayList<String> names = new ArrayList<String>();
				ArrayList<Double> amount = new ArrayList<Double>();
				double payment = bill.getAmount()/group.getMembers().size();
				payment = Math.ceil( payment * 100.0 ) / 100.0;
				for(String email: group.getMembers()){
					Person payer = datastore.getPerson(email);
					names.add(payer.getName());
					if(bill.getPeeps().contains(email)){
						amount.add(payment);
					} else {
						amount.add(0.0);
					}
				}
				req.setAttribute("payment", payment);
				req.setAttribute("names", names);
				req.setAttribute("amount", amount);
				req.getRequestDispatcher("/WEB-INF/showBill.jsp").forward(req, resp);
				break;
			case "debt":	String name = splitPath[length-1];
				Person debtor = null;
				for(String email : emails){
					debtor = datastore.getPerson(email);
					if(debtor.getName().equalsIgnoreCase(name)){
						break;
					}
				}				
				ArrayList<Debt> debtList = new ArrayList<Debt>();
				List<Bill> billList = datastore.getBills(debtor);
				List<Debt> debtListz = datastore.getDebtsDebtor(person);
				List<Bill> debtBills = new ArrayList<Bill>();
				Double balance = 0.0;
				for(Bill billz : billList){
					Double amountz = Math.ceil(billz.getAmount()*100/numRoommates)/100;
					if(billz.getPeeps().contains(userEmail)){				
						debtList.add(new Debt.Builder(billz.getName() + " (Bill)", amountz, debtor, person).setDateCreated(billz.getDateCreated()).setBillId(billz.getId()).build());
						balance -= amountz;
					} else {
						debtList.add(new Debt.Builder(billz.getName() + " (Bill)", amountz, debtor, person).setDateCreated(billz.getDateCreated()).setDatePaid(new Date()).setBillId(billz.getId()).build());
					}
				}
				for(Bill billz : bills){
					Double amountz = Math.ceil(billz.getAmount()*100/numRoommates)/100;
					if(billz.getPeeps().contains(debtor.getEmail())){	
						debtList.add(new Debt.Builder(billz.getName() + " (Bill)", amountz, person, debtor).setDateCreated(billz.getDateCreated()).setBillId(billz.getId()).build());
						balance += amountz;
					} else {
						debtList.add(new Debt.Builder(billz.getName() + " (Bill)", amountz, person, debtor).setDateCreated(billz.getDateCreated()).setDatePaid(new Date()).setBillId(billz.getId()).build());
					}
				}
				for(Debt debt: posDebts){
					if(debt.getDebtor().equals(debtor.getEmail())){
						debtList.add(debt);
						if(debt.getDatePaid() == null){
							balance += debt.getAmount();
						}
					}
				}
				for(Debt debt: debtListz){
					if(debt.getOwner().equals(debtor.getEmail())){
						debtList.add(debt);
						if(debt.getDatePaid() == null){
							balance -= debt.getAmount();
						}
					}
				}
				
				req.setAttribute("Amount", Math.ceil(balance*100)/100);
				Collections.sort(debtList, Collections.reverseOrder());
				req.setAttribute("DebtListz", debtList);
				req.setAttribute("debtor", name);
				req.getRequestDispatcher("/WEB-INF/showDebt.jsp").forward(req, resp);
				break;
			case "request":	id = Long.parseLong(splitPath[length-1], 10);
				MaintenanceRequest request = datastore.getMaintenanceRequest(id);
				req.setAttribute("Request", request);
				req.getRequestDispatcher("/WEB-INF/showRequest.jsp").forward(req, resp);
				break;
			case "building": 	id = Long.parseLong(splitPath[length-1], 10);
				Building building = datastore.getBuilding(id);
				req.setAttribute("building", building);
				req.getRequestDispatcher("/WEB-INF/showBuilding.jsp").forward(req, resp);

			default:		resp.sendRedirect("/home");
				break;
		}
	}
}
