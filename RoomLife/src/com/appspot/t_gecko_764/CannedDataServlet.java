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
			
			
			
			Person pett = new Person("Pett","pett@example.com");
			Person david = new Person("David","david@example.com");
			
			Group group3 = new Group("TestGroup3");
			datastore.saveGroup(group3);
			group3.setAddress("Apt 233");
			group3.addMember(pett);
			group3.addMember(david);
			datastore.saveGroup(group3);
			datastore.savePerson(pett);
			datastore.savePerson(david);
			
			// save mock users to the datastore
			datastore.savePerson(alice);
			datastore.savePerson(bob);
			datastore.savePerson(candice);
			datastore.savePerson(danny);
			
			//create bill one, not including bob
			Calendar deadline = new GregorianCalendar();
			deadline.add(Calendar.DAY_OF_MONTH, 5);
			Calendar date = new GregorianCalendar();
			date.add(Calendar.DAY_OF_MONTH, -1);
			Bill bill1 = new Bill.Builder("Water", 11.11, alice).setGroup(group).setDateDeadline(deadline.getTime()).setDateCreated(date.getTime()).build();
			bill1.getPeeps().remove(2);
			System.out.println("start datastore save");
			datastore.saveBill(bill1);		
			System.out.println("end datastore save");
			
			// Bill that include all the group
			Calendar deadline2 = new GregorianCalendar();
			deadline2.add(Calendar.DAY_OF_MONTH, 10);
			Bill bill2 = new Bill.Builder("Electricity", 22.22, alice).setGroup(group).setDateDeadline(deadline.getTime()).build();
			datastore.saveBill(bill2);			
					
			// Bill that includes all the group
			Calendar deadline3 = new GregorianCalendar();
			deadline3.add(Calendar.DAY_OF_MONTH, 15);
			Bill bill3 = new Bill.Builder("Gas", 33.33, bob).setGroup(group).setDateDeadline(deadline.getTime()).build();
			datastore.saveBill(bill3);
			
			Debt debt1 = new Debt.Builder("debt1", 11.11, alice, bob).build();
			Calendar date1 = new GregorianCalendar();
			date1.add(Calendar.DAY_OF_MONTH, -5);
			debt1.setDateCreated(date1.getTime());
			datastore.saveDebt(debt1);
			
			Debt debt2 = new Debt.Builder("debt2", 22.22, alice, candice).build();
			Calendar date2 = new GregorianCalendar();
			date2.add(Calendar.DAY_OF_MONTH, -4);
			debt2.setDateCreated(date1.getTime());
			datastore.saveDebt(debt2);
			
			Debt debt3 = new Debt.Builder("debt3", 33.33, alice, danny).build();
			Calendar date3 = new GregorianCalendar();
			date3.add(Calendar.DAY_OF_MONTH, -2);
			debt3.setDateCreated(date1.getTime());
			datastore.saveDebt(debt3);

			Debt debt4 = new Debt.Builder("debt4", 44.44, bob, alice).build();
			Calendar date4 = new GregorianCalendar();
			date4.add(Calendar.DAY_OF_MONTH, -7);
			debt4.setDateCreated(date1.getTime());
			datastore.saveDebt(debt4);			
			
			Debt debt5 = new Debt.Builder("debt5", 55.55, bob, candice).build();
			Calendar date5 = new GregorianCalendar();
			date5.add(Calendar.DAY_OF_MONTH, -5);
			debt5.setDateCreated(date1.getTime());
			datastore.saveDebt(debt5);
			
			Debt debt6 = new Debt.Builder("debt6", 66.66, bob, danny).build();
			datastore.saveDebt(debt6);
			
			MaintenanceRequest request1 = new MaintenanceRequest.Builder("request1", alice, group).setLocation("room1").setPriority("priority1")
					.setDetails("Details1Details1Details1Details1Details1Details1Details1 Details1Details1Details1Details1Details1Details1Details1\nDetails1Details1Details1Details1Details1Details1Details1").build();
			datastore.saveMaintenanceRequest(request1);
			group.addMaintenanceRequest(request1);
			datastore.saveGroup(group);
						
			MaintenanceRequest request2 = new MaintenanceRequest.Builder("request2", bob, group).setLocation("room2").setPriority("priority2")
					.setDetails("Details2Details2Details2Details2Details2Details2Details2 Details2Details2Details2Details2Details2Details2Details2\nDetails2Details2Details2Details2Details2Details2Details2").build();
			datastore.saveMaintenanceRequest(request2);
			group.addMaintenanceRequest(request2);
			datastore.saveGroup(group);
			
			Landlord evan = new Landlord("Evan", "evan@example.com");
			datastore.saveLandlord(evan);
			Building building1= new Building ("Unit 1","evan@example.com");
			building1.addGroup(group.getId());
			building1.addGroup(group3.getId());
			Building building2= new Building ("Unit 2","evan@example.com");
			building2.addGroup(group2.getId());
			
			datastore.saveBuilding(building1);
			datastore.saveBuilding(building2);
			
			evan.addBuilding(building1.getId());
			evan.addBuilding(building2.getId());
			
			datastore.saveLandlord(evan);

			
			resp.getWriter().println("Finished loading data");
		} else {
			resp.getWriter().println("Already loaded data");
		}
	}
}
