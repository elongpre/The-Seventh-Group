package com.appspot.t_gecko_764;

import java.util.ArrayList;

public class Group {
	private String name;
	private String address;
	private ArrayList<Person> members;
	private Building building;
	
	public Group(String name, Person member){
		this.name = name;
		this.members = new ArrayList<Person>();
		members.add(member);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<Person> getMembers() {
		return members;
	}
	public void addMember(Person member) {
		if(!members.contains(member)){
			members.add(member);
			member.addGroup(this);
		}	
	}
	public void removeMember(Person member){
		members.remove(member);
		member.removeGroup(this);
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	
	
	
}