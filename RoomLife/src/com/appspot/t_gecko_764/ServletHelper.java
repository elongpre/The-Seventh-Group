package com.appspot.t_gecko_764;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHelper {

	public static void initializeServlet(HttpServletRequest req, HttpServletResponse resp, Person person){
		DataStore datastore = DataStore.getInstance();
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
				if(debt.getDebtor().equals(email) && (debt.getDatePaid() == null)){
					amount += debt.getAmount();
				}
			}
			for(Debt debt: negDebts){
				if(debt.getOwner().equals(email) && (debt.getDatePaid() == null)){
					amount -= debt.getAmount();
				}
			}
			charges.add(Math.ceil(amount*100)/100);
		}
		
		req.setAttribute("DebtNames", roommates);
		req.setAttribute("DebtAmounts", charges);
		
		List<MaintenanceRequest> requests = datastore.getMaintenanceRequests(person);			
		req.setAttribute("RequestList", requests);
	}
}
