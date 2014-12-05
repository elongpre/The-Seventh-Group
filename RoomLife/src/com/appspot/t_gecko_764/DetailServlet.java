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
		String userEmail = person.getEmail();
		List<Bill> bills = datastore.getBills(person);
		req.setAttribute("BillList", bills);

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
		for(String email : emails){
			Double amount = 0.0;
			List<Bill> billList = datastore.getBillsEmail(email);
			for(Bill bill : billList){
				if(bill.getPeeps().contains(userEmail)){
					amount -= Math.ceil(bill.getAmount()*100/numRoommates)/100;
				}
			}
			for(Bill bill : bills){
				if(bill.getPeeps().contains(email)){
					amount += Math.ceil(bill.getAmount()*100/numRoommates)/100;
				}
			}
			for(Debt debt: posDebts){
				if(debt.getDebtor().equals(email)){
					amount += debt.getAmount();
				}
			}
			for(Debt debt: negDebts){
				if(debt.getOwner().equals(email)){
					amount -= debt.getAmount();
				}
			}
			charges.add(Math.ceil(amount*100)/100);
		}
		
		req.setAttribute("DebtNames", roommates);
		req.setAttribute("DebtAmounts", charges);
		
		List<MaintenanceRequest> requests = datastore.getMaintenanceRequests(person);
		req.setAttribute("RequestList", requests);

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
				req.setAttribute("names", names);
				req.setAttribute("amount", amount);
				req.getRequestDispatcher("/WEB-INF/showBill.jsp").forward(req, resp);
				break;
			case "debt":	String name = splitPath[length-1];
				Person debtor = null;
				int index = 0;
				for(String email : emails){
					debtor = datastore.getPerson(email);
					if(debtor.getName().equalsIgnoreCase(name)){
						break;
					} else {
						index ++;
					}
				}				
				Double debtAmount = charges.get(index);
				ArrayList<Debt> debtList = new ArrayList<Debt>();
				List<Bill> billList = datastore.getBills(debtor);
				List<Debt> debtListz = datastore.getDebtsDebtor(person);
				for(Bill billz : billList){
					if(billz.getPeeps().contains(userEmail)){
						Double amountz = Math.ceil(billz.getAmount()*100/numRoommates)/100;
						debtList.add(new Debt.Builder(billz.getName(), amountz, debtor, person).build());	
					}
				}
				for(Bill billz : bills){
					if(billz.getPeeps().contains(debtor.getEmail())){
						Double amountz = Math.ceil(billz.getAmount()*100/numRoommates)/100;
						debtList.add(new Debt.Builder(billz.getName(), amountz, person, debtor).build());
					}
				}
				for(Debt debt: posDebts){
					if(debt.getDebtor().equals(debtor.getEmail())){
						debtList.add(debt);
					}
				}
				for(Debt debt: debtListz){
					if(debt.getOwner().equals(debtor.getEmail())){
						debtList.add(debt);
					}
				}
				
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
