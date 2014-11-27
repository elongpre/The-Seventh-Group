package com.appspot.t_gecko_764;

import static com.googlecode.objectify.ObjectifyService.ofy;


public class DataStore {
	private static DataStore uniqueInstance;
	private DataStore(){
		
	}
	
	public static DataStore getInstance(){
		if (uniqueInstance==null){
			uniqueInstance= new DataStore();
		}
		return uniqueInstance;
	}
	
	Person getPerson(String email){
		 return ofy().load().type(Person.class).filter("email", email).first().now();
	}
	
	public void savePerson(Person person){
		ofy().save().entity(person).now();
		
	}
}
