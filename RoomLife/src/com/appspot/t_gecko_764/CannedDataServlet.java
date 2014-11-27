package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CannedDataServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Person alice = new Person("Alice", "alice@example.com");
		Person bob = new Person("Bob", "bob@example.com");
		Person candice = new Person("Candice", "candice@example.com");
		Person danny = new Person("Danny", "danny@example.com");
		Group group = new Group("TestGroup", alice);
		group.addMember(bob);
		group.addMember(candice);
		group.addMember(danny);
		Group guysGroup = new Group("GuysGroup", bob);
		guysGroup.addMember(danny);		
		
		Calendar deadline = new GregorianCalendar();
		deadline.add(Calendar.DAY_OF_MONTH, 5);
		Bill bill1 = new Bill.Builder("Water", 11.11, alice).setGroup(group).setDateDeadline(deadline).build();
		alice.addBill(bill1);
		deadline.add(Calendar.DAY_OF_MONTH, 5);
		Bill bill2 = new Bill.Builder("Electricity", 22.22, alice).setGroup(group).setDateDeadline(deadline).build();
		alice.addBill(bill2);
		deadline.add(Calendar.DAY_OF_MONTH, 5);
		Bill bill3 = new Bill.Builder("Gas", 33.33, bob).setGroup(group).setDateDeadline(deadline).build();
		bob.addBill(bill3);
		deadline.add(Calendar.DAY_OF_MONTH, 5);
		Bill bill4 = new Bill.Builder("Porn", 44.44, bob).setGroup(guysGroup).setDateDeadline(deadline).build();
		bob.addBill(bill4);
		
		Debt debt1 = new Debt.Builder("debt1", 11.11, alice, bob).build();
		Debt debt2 = new Debt.Builder("debt2", 22.22, alice, candice).build();
		Debt debt3 = new Debt.Builder("debt3", 33.33, alice, danny).build();
		Debt debt4 = new Debt.Builder("debt4", 44.44, bob, alice).build();
		Debt debt5 = new Debt.Builder("debt4", 55.55, bob, candice).build();
		Debt debt6 = new Debt.Builder("debt4", 66.66, bob, danny).build();		
		
		MaintenanceRequest request1 = new MaintenanceRequest.Builder("request1", alice, group).setLocation("room1").setPriority("priority1")
				.setDetails("Details1Details1Details1Details1Details1Details1Details1 Details1Details1Details1Details1Details1Details1Details1\nDetails1Details1Details1Details1Details1Details1Details1").build();
		MaintenanceRequest request2 = new MaintenanceRequest.Builder("request1", bob, group).setLocation("room2").setPriority("priority2")
				.setDetails("Details2Details2Details2Details2Details2Details2Details2 Details2Details2Details2Details2Details2Details2Details2\nDetails2Details2Details2Details2Details2Details2Details2").build();
		
		resp.getWriter().println("Finished loading data");
		
	}
}
