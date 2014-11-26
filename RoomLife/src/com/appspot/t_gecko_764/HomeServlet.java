package com.appspot.t_gecko_764;

import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse resp){
			
		}
		public static ArrayList<Bill> getBills(){
			ArrayList<Bill> bills = new ArrayList<Bill>();
			bills.add(new Bill ());
			bills.add(new Bill ());
			return bills;
		}
		
		public static ArrayList<Debt> getDebts(){
			return new ArrayList<Debt>();
		}
		public static ArrayList<MaintenanceRequest> getRequests(){
			return new ArrayList<MaintenanceRequest>();
		}
}
