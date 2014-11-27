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
	public void setRoommates(ArrayList<Person> roommates) {
		this.roommates = roommates;
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	
	public void addBill(Bill bill) {
		this.bills.add(bill);
	}
	
}
