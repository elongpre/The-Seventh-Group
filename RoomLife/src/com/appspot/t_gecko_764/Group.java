package com.appspot.t_gecko_764;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Group {
	@Id private Long id;
	@Index private String name;
	private String address;
	private ArrayList<String> members;
	//private Building building;
	private ArrayList<Long> mainreq;
	
	private Group(){}
	
	public Group(String name, Person member){
		this.name = name;
		this.members = new ArrayList<String>();
		members.add(member.getEmail());
	}
	
	public Long getId() {
		return id;
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
	public ArrayList<String> getMembers() {
		return members;
	}
	public void addMember(Person member) {
		if(!members.contains(member.getEmail())){
			members.add(member.getEmail());
			member.addGroup(this);
		}	
	}
	public void removeMember(Person member){
		members.remove(member.getEmail());
		member.removeGroup(this);
	}
//	public Building getBuilding() {
//		return building;
//	}
//	public void setBuilding(Building building) {
//		this.building = building;
//	}
	
	public void addMaintenanceRequest(MaintenanceRequest req){
		this.mainreq.add(req.getId());
	}

	public ArrayList<Long> getMainreq() {
		return mainreq;
	}

	public void setMainreq(ArrayList<Long> mainreq) {
		this.mainreq = mainreq;
	}
	
	
	
}