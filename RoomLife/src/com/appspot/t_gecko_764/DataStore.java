package com.appspot.t_gecko_764;

import java.util.List;

import static com.appspot.t_gecko_764.OfyService.ofy;

public class DataStore {
	private static final DataStore uniqueInstance = new DataStore();
	
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
	
	public Group getGroup(Long id){
		return ofy().load().type(Group.class).id(id).now();
	}
	
	public void saveGroup(Group group){
		ofy().save().entity(group).now();
	}
	
	public void saveBill (Bill bill){
		ofy().save().entity(bill).now();
	}
	
	public List<Bill> getBills(Person person){
		return ofy().load().type(Bill.class).filter("owner", person.getEmail()).list();
	}
	public Bill getBill(Long id){
		return ofy().load().type(Bill.class).id(id).now();
	}
	
	public void saveDebt(Debt debt){
		ofy().save().entity(debt).now();
	}
	public List<Debt> getDebts(Person person){
		return ofy().load().type(Debt.class).filter("owner", person.getEmail()).list();
	}
	public Debt getDebt(Long id){
		return ofy().load().type(Debt.class).id(id).now();
	}
	
	public void saveMaintenanceRequest(MaintenanceRequest request){
		OfyService.ofy().save().entity(request).now();
	}
	public List<MaintenanceRequest> getMaintenanceRequests(Person person){
		return ofy().load().type(MaintenanceRequest.class).filter("owner", person.getEmail()).list();
	}
	public MaintenanceRequest getMaintenanceRequest(Long id){
		return ofy().load().type(MaintenanceRequest.class).id(id).now();
	}
	public Landlord getLandlord(String email){
		return new Landlord("Name","");
		//return ofy().load().type(Landlord.class).filter("email", email).first().now();
	}
	
	public void saveLandlord(Landlord landlord){
		OfyService.ofy().save().entity(landlord).now();
	}
	
	public Building getBuilding(Long id){
		return ofy().load().type(Building.class).id(id).now();
	}
	public void saveBuilding(Building building){
		OfyService.ofy().save().entity(building).now();
	}
	
	
}
