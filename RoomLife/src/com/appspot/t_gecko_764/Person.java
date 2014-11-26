package com.appspot.t_gecko_764;

import java.util.ArrayList;

public class Person {
	private String name;
	private ArrayList<Person> roommates;
	private ArrayList<Group> groups;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}