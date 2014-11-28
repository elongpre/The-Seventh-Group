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
	private ArrayList<String> roommates;
	private ArrayList<Long> groups;
	
	private Person(){}
	
	public Person(String name, String email){
		this.name = name;
		this.email = email;
		this.groups = new ArrayList<Long>();
		this.roommates = new ArrayList<String>();
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
	public ArrayList<Long> getGroups() {
		return groups;
	}
	public void addGroup(Group group) {
		if(!groups.contains(group.getId())){
			groups.add(group.getId());
		}
	}
	public void removeGroup(Group group) {
		groups.remove(group.getId());
	}		
}
