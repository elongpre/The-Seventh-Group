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
		DataStore datastore = DataStore.getInstance();
		Person alice = new Person("Alice", "alice@example.com");
		datastore.savePerson(alice);
		Person bob = new Person("Bob", "bob@example.com");
		datastore.savePerson(bob);
		Person candice = new Person("Candice", "candice@example.com");
		datastore.savePerson(candice);
		Person danny = new Person("Danny", "danny@example.com");
		datastore.savePerson(danny);
//		Group group = new Group("TestGroup", alice);
//		group.addMember(bob);
//		group.addMember(candice);
//		group.addMember(danny);
//		datastore.saveGroup(group);
//		Group guysGroup = new Group("GuysGroup", bob);
//		guysGroup.addMember(danny);	
//		datastore.saveGroup(guysGroup);
//		
//		Calendar deadline = new GregorianCalendar();
//		deadline.add(Calendar.DAY_OF_MONTH, 5);
//		Bill bill1 = new Bill.Builder("Water", 11.11, alice).setGroup(group).setDateDeadline(deadline).build();
//		alice.addBill(bill1);
//		datastore.saveBill(bill1);
//		deadline.add(Calendar.DAY_OF_MONTH, 5);
//		Bill bill2 = new Bill.Builder("Electricity", 22.22, alice).setGroup(group).setDateDeadline(deadline).build();
//		alice.addBill(bill1);
//		datastore.saveBill(bill2);
//		deadline.add(Calendar.DAY_OF_MONTH, 5);
//		Bill bill3 = new Bill.Builder("Gas", 33.33, bob).setGroup(group).setDateDeadline(deadline).build();
//		bob.addBill(bill3);
//		datastore.saveBill(bill3);
//		deadline.add(Calendar.DAY_OF_MONTH, 5);
//		Bill bill4 = new Bill.Builder("Porn", 44.44, bob).setGroup(guysGroup).setDateDeadline(deadline).build();
//		bob.addBill(bill4);
//		datastore.saveBill(bill4);
//		
//		Debt debt1 = new Debt.Builder("debt1", 11.11, alice, bob).build();
//		datastore.saveDebt(debt1);
//		Debt debt2 = new Debt.Builder("debt2", 22.22, alice, candice).build();
//		datastore.saveDebt(debt2);
//		Debt debt3 = new Debt.Builder("debt3", 33.33, alice, danny).build();
//		datastore.saveDebt(debt3);
//		Debt debt4 = new Debt.Builder("debt4", 44.44, bob, alice).build();
//		datastore.saveDebt(debt4);
//		Debt debt5 = new Debt.Builder("debt4", 55.55, bob, candice).build();
//		datastore.saveDebt(debt5);
//		Debt debt6 = new Debt.Builder("debt4", 66.66, bob, danny).build();
//		datastore.saveDebt(debt6);
//		
//		MaintenanceRequest request1 = new MaintenanceRequest.Builder("request1", alice, group).setLocation("room1").setPriority("priority1")
//				.setDetails("Details1Details1Details1Details1Details1Details1Details1 Details1Details1Details1Details1Details1Details1Details1\nDetails1Details1Details1Details1Details1Details1Details1").build();
//		datastore.saveMaintenanceRequest(request1);
//		MaintenanceRequest request2 = new MaintenanceRequest.Builder("request1", bob, group).setLocation("room2").setPriority("priority2")
//				.setDetails("Details2Details2Details2Details2Details2Details2Details2 Details2Details2Details2Details2Details2Details2Details2\nDetails2Details2Details2Details2Details2Details2Details2").build();
//		datastore.saveMaintenanceRequest(request2);
		resp.getWriter().println("Finished loading data");
		
	}
}
