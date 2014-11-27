package com.appspot.t_gecko_764;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Person {
	@Id Long id;
	private String name;
	@Index private String email;
	private ArrayList<Person> roommates;
	private ArrayList<Group> groups;
	private ArrayList<Bill> bills;
	
	public Person(String name, String email){
		this.name = name;
		this.email = email;
		this.bills = new ArrayList<Bill>();
		this.groups = new ArrayList<Group>();
		this.roommates = new ArrayList<Person>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Person> getRoommates() {
		return roommates;
	}
	public void addRoommate(Person roommate) {
		roommates.add(roommate);
	}
	public void removeRoommates(Person roommate){
		roommates.remove(roommate);
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void addGroup(Group group) {
		if(!groups.contains(group)){
			groups.add(group);
		}

	}
	public void removeGroup(Group group) {
		groups.remove(group);
	}	
	public void addBill(Bill bill) {
		this.bills.add(bill);
	}
	
}
