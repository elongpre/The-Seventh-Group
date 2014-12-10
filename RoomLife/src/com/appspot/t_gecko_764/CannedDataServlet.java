package com.appspot.t_gecko_764;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CannedDataServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		DataStore datastore = DataStore.getInstance();
		if (datastore.getPerson("alice@example.com") == null){		
			Person alice = new Person("Alice", "alice@example.com");
			Person bob = new Person("Bob", "bob@example.com");
			Person candice = new Person("Candice", "candice@example.com");
			Person danny = new Person("Danny", "danny@example.com");
						
			Group group = new Group("TestGroup");
			datastore.saveGroup(group);
			group.setAddress("Apt 125");
			group.addMember(alice);
			group.addMember(bob);
			group.addMember(candice);
			group.addMember(danny);
			
			datastore.saveGroup(group); //id should be auto-generated here
			Long id = group.getId();
						
			Person rob=new Person ("Rob","rob@example.com");
			Person matt= new Person("Matt","matt@example.com");
			
			Group group2= new Group("TestGroup2");
			datastore.saveGroup(group2);
			group2.setAddress("Apt 223");
			group2.addMember(rob);
			group2.addMember(matt);
			
			datastore.saveGroup(group2);
			Long id2 = group2.getId();
			
			datastore.savePerson(matt);
			datastore.savePerson(rob);
			
			// save mock users to the datastore
			datastore.savePerson(alice);
			datastore.savePerson(bob);
			datastore.savePerson(candice);
			datastore.savePerson(danny);
			
			//create bill one, not including bob
			Calendar deadline = new GregorianCalendar();
			deadline.add(Calendar.DAY_OF_MONTH, 5);
			Bill bill1 = new Bill.Builder("Water", 11.11, alice).setGroup(group).setDateDeadline(deadline.getTime()).build();
			bill1.getPeeps().remove(1);
			System.out.println("start datastore save");
			datastore.saveBill(bill1);		
			System.out.println("end datastore save");
			
			// Bill that include all the group
			deadline.add(Calendar.DAY_OF_MONTH, 5);
			Bill bill2 = new Bill.Builder("Electricity", 22.22, alice).setGroup(group).setDateDeadline(deadline.getTime()).build();
			datastore.saveBill(bill2);			
					
			// Bill that includes all the group
			deadline.add(Calendar.DAY_OF_MONTH, 5);
			Bill bill3 = new Bill.Builder("Gas", 33.33, bob).setGroup(group).setDateDeadline(deadline.getTime()).build();
			datastore.saveBill(bill3);
			deadline.add(Calendar.DAY_OF_MONTH, 5);
			
			Debt debt2 = new Debt.Builder("debt2", 22.22, alice, candice).build();
			datastore.saveDebt(debt2);
			candice.addDebt(debt2.getId());
			datastore.savePerson(candice);
			
			Debt debt3 = new Debt.Builder("debt3", 33.33, alice, danny).build();
			datastore.saveDebt(debt3);
			danny.addDebt(debt3.getId());
			datastore.savePerson(danny);
						
			Debt debt4 = new Debt.Builder("debt4", 44.44, bob, alice).build();
			datastore.saveDebt(debt4);
			alice.addDebt(debt4.getId());
			datastore.savePerson(alice);
			
			
			Debt debt5 = new Debt.Builder("debt5", 55.55, bob, candice).build();
			datastore.saveDebt(debt5);
			candice.addDebt(debt5.getId());
			datastore.savePerson(candice);
			
			Debt debt6 = new Debt.Builder("debt6", 66.66, bob, danny).build();
			datastore.saveDebt(debt6);
			danny.addDebt(debt6.getId());
			datastore.savePerson(danny);
			
			MaintenanceRequest request1 = new MaintenanceRequest.Builder("request1", alice, group).setLocation("room1").setPriority("priority1")
					.setDetails("Details1Details1Details1Details1Details1Details1Details1 Details1Details1Details1Details1Details1Details1Details1\nDetails1Details1Details1Details1Details1Details1Details1").build();
			datastore.saveMaintenanceRequest(request1);
						
			MaintenanceRequest request2 = new MaintenanceRequest.Builder("request1", bob, group).setLocation("room2").setPriority("priority2")
					.setDetails("Details2Details2Details2Details2Details2Details2Details2 Details2Details2Details2Details2Details2Details2Details2\nDetails2Details2Details2Details2Details2Details2Details2").build();
			datastore.saveMaintenanceRequest(request2);			
			
			Landlord evan = new Landlord("Evan", "evan@example.com");
			datastore.saveLandlord(evan);
			Building building1= new Building ("Unit 1","evan@example.com");
			building1.addGroup(group.getId());
			Building building2= new Building ("Unit 2","evan@example.com");
			building2.addGroup(group2.getId());
			
			datastore.saveBuilding(building1);
			datastore.saveBuilding(building2);
			
			evan.addBuilding(building1.getId());
			evan.addBuilding(building2.getId());
			
			datastore.saveLandlord(evan);
			Landlord Pizza=datastore.getLandlordViaName(evan.getId());
			
			if(Pizza!=null)
			System.out.println(Pizza.toString());
			
			resp.getWriter().println("Finished loading data");
		} else {
			resp.getWriter().println("Already loaded data");
		}
	}
}
