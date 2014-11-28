package com.appspot.t_gecko_764;



import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;



public class DataStore {
	private static final DataStore uniqueInstance = new DataStore();
	
	private DataStore(){
	}
	
	public static DataStore getInstance(){
		return uniqueInstance;
	}
	
	public Person getPerson(String email){
		 return OfyService.ofy().load().type(Person.class).filter("email", email).first().now();
	}
	
	public Person getPersonviaId(Person person){
		return OfyService.ofy().load().type(Person.class).id(person.id).now();
	}
	
	public void savePerson(Person person){
		OfyService.ofy().save().entity(person).now();
	}
	
	public Group getGroup(Person person, String groupName){
		ArrayList<Group> x= person.getGroups();
		for(Group name: x ){
			String findGroup= name.getName();
			if (findGroup.equals(groupName)){
				return name;
			}
		}
		return null;
	}
	
	public void saveGroup(Group group){
		OfyService.ofy().save().entity(group).now();
	}
	
	public void saveBill (Bill bill){
		OfyService.ofy().save().entity(bill).now();
	}
	public List<Bill> getBills(Person person){
		return OfyService.ofy().load().type(Bill.class).filter("owner", person).list();
	}
	
	public void saveDebt(Debt debt){
		OfyService.ofy().save().entity(debt).now();
	}
	public List<Debt> getDebts(Person person){
		return OfyService.ofy().load().type(Debt.class).filter("owner", person).list();
	}
	
	public void saveMaintenanceRequest(MaintenanceRequest request){
		OfyService.ofy().save().entity(request).now();
		
	}
}
