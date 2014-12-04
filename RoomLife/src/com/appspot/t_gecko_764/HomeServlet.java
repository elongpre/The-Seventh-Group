package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class HomeServlet extends HttpServlet{

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
			List<Debt> debts = datastore.getDebts(person);
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
				for(Debt debt: debts){
					if(debt.getDebtor() == email){
						amount += debt.getAmount();
					}
				}
				charges.add(Math.ceil(amount*100)/100);
			}
			
			req.setAttribute("DebtNames", roommates);
			req.setAttribute("DebtAmounts", charges);
			
			List<MaintenanceRequest> requests = datastore.getMaintenanceRequests(person);
			req.setAttribute("RequestList", requests);
			req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
		}
}
