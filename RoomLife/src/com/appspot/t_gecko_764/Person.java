package com.appspot.t_gecko_764;

import java.util.ArrayList;

import javax.jdo.annotations.Persistent;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Person {
	@Id Long id;
	@Index private String email;
	private Long group;
	private String name;
	private ArrayList<String> roommates;
	@Index private ArrayList<Long> bills;
	private ArrayList<Long> debts;
	private ArrayList<Long> requests;

	
	private Person(){}
	
	public Person(String name, String email){
		this.name = name;
		this.email = email;
		this.roommates = new ArrayList<String>();
		this.bills= new ArrayList<Long>();
		this.debts=new ArrayList<Long>();
		this.requests= new ArrayList<Long>();
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
	public ArrayList<String> getRoommates() {
		return roommates;
	}
	public void addRoommate(Person roommate) {
		roommates.add(roommate.getEmail());
	}
	public void removeRoommates(Person roommate){
		roommates.remove(roommate.getEmail());
	}
	public Long getGroup() {
		return group;
	}
	public void addGroup(Long id) {
		this.group = id;
	}
	public void removeGroup(Group group) {
		this.group = null;
	}

	public ArrayList<Long> getBills() {
		return bills;
	}

	public void setBills(ArrayList<Long> bills) {
		this.bills = bills;
	}

	public ArrayList<Long> getDebts() {
		return debts;
	}

	public void setDebts(ArrayList<Long> debts) {
		this.debts = debts;
	}

	public ArrayList<Long> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Long> requests) {
		this.requests = requests;
	}
	
	public void addBill(Long bill){
		this.bills.add(bill);
	}
	
	public void addDebt(Long debt){
		this.debts.add(debt);
	}
	
	public void addRequest(Long req){
		this.requests.add(req);
	}

}
