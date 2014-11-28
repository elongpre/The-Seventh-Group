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

public class HomeServlet extends HttpServlet{

		public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
			DataStore datastore = DataStore.getInstance();
			Person alice = datastore.getPerson("alice@example.com");
			List<Bill> bills = datastore.getBills(alice);
			req.setAttribute("BillList", bills);
			req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
		}
		
		private List<Bill> getBills(){
			DataStore datastore = DataStore.getInstance();
			Person alice = datastore.getPerson("alice@example.com");
			return datastore.getBills(alice);
		}
		
		private ArrayList<Debt> getDebts(){
			return new ArrayList<Debt>();
		}
		private ArrayList<MaintenanceRequest> getRequests(){
			return new ArrayList<MaintenanceRequest>();
		}
}
