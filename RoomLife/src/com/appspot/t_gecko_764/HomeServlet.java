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
		    
		    // get bills where person is owner
			DataStore datastore = DataStore.getInstance();
			Landlord landlord=datastore.getLandlord(user.getEmail());
			Person person = datastore.getPerson("alice@example.com");
			
			if(landlord!=null){
				List<Long> buildingsKey=landlord.getBuildings();
				ArrayList<Building> buildings=new ArrayList<Building>();
				if(buildingsKey!=null){
					for(Long buildingKey:buildingsKey){
						
						buildings.add(datastore.getBuilding(buildingKey));
						
					}
				}
				req.setAttribute("Buildings", buildings);
				req.getRequestDispatcher("/WEB-INF/landlordHome.jsp").forward(req, resp);
				
			}else if (person!=null){
			List<Bill> bills = datastore.getBills(person);
			
			
			// get bills where person is not the owner
			/*Bill bill;
			List<Long> externalBills = person.getBills(); 
			if(externalBills!=null){
				for(Long id: externalBills){
					bill=datastore.getBill(id);
					bills.add(bill);
				}
			}*/
			
			
			
			
			
			req.setAttribute("BillList", bills);
			
			
			
			List<Debt> debts = datastore.getDebts(person);
			Debt debt;
			List<Long> externalDebts = person.getDebts(); 
			
			/*if(externalDebts!=null){
				for(Long id: externalDebts){
					debt=datastore.getDebt(id);
					debts.add(debt);
				}
			}*/
			
			req.setAttribute("DebtList", debts);
			
			List<MaintenanceRequest> requests = datastore.getMaintenanceRequests(person);
			/*MaintenanceRequest mRequest;
			List<Long> externalRequests = person.getRequests();
			
			if(externalRequests!=null){
				for(Long id: externalRequests){
					mRequest=datastore.getMaintenanceRequest(id);
					requests.add(mRequest);
			
				}
			}*/
			
			req.setAttribute("RequestList", requests);
			req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/LoginPage.jsp");
		}
	}
}
