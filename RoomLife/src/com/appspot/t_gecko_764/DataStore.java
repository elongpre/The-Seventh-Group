package com.appspot.t_gecko_764;



import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;
import com.googlecode.objectify.ObjectifyService;


public class DataStore {
	static {
		ObjectifyService.register(Person.class);
//		ObjectifyService.register(Group.class);
//		
//		ObjectifyService.register(Bill.class);
//		ObjectifyService.register(Debt.class);
//		ObjectifyService.register(MaintenanceRequest.class);
    }
	
	private static DataStore uniqueInstance = new DataStore();
	
	private DataStore(){}
	
	public static DataStore getInstance(){
		return uniqueInstance;
	}
	
	public Person getPerson(String email){
		 return ofy().load().type(Person.class).filter("email", email).first().now();
	}
	
	public Person getPersonviaId(Person person){
		return ofy().load().type(Person.class).id(person.id).now();
	}
	
	public void savePerson(Person person){
		ofy().save().entity(person).now();
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
		ofy().save().entity(group).now();
	}
	
	public void saveBill (Bill bill){
		ofy().save().entity(bill).now();
	}
	public List<Bill> getBills(Person person){
		return ofy().load().type(Bill.class).filter("owner", person).list();
	}
	
	public void saveDebt(Debt debt){
		ofy().save().entity(debt).now();
	}
	public List<Debt> getDebts(Person person){
		return ofy().load().type(Debt.class).filter("owner", person).list();
	}
	
	public void saveMaintenanceRequest(MaintenanceRequest request){
		ofy().save().entity(request).now();
		
	}
}
