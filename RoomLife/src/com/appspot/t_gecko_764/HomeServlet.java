package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
			req.setAttribute("BillList", getBills());
			req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
		}
		private ArrayList<Bill> getBills(){
			ArrayList<Bill> bills = new ArrayList<Bill>();
			for(int i=1; i <= 20; i++){
				Bill bill = new Bill();
				bill.setName("Bill " + i);
				bills.add(bill);
			}
			return bills;
		}
		
		private ArrayList<Debt> getDebts(){
			return new ArrayList<Debt>();
		}
		private ArrayList<MaintenanceRequest> getRequests(){
			return new ArrayList<MaintenanceRequest>();
		}
}
