package com.appspot.t_gecko_764;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Building {
	@Id Long id;
	private String name;
	private String landlord;
	private ArrayList<Long> groups;
	
	private Building(){
		
	}
			
	
	public Building(String name,String landlord){
		this.name=name;
		this.landlord=landlord;
		this.groups=new ArrayList<Long>();
	}
	
	public Long getId(){
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLandlord() {
		return landlord;
	}
	public void setLandlord(String landlord) {
		this.landlord = landlord;
	}
	public ArrayList<Long> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Long> groups) {
		this.groups = groups;
	}
	public void addGroup(Long id){
		this.groups.add(id);
	}
	public void deleteGroup(Long id){
		this.groups.remove(id);
	}
	
}