package com.appspot.t_gecko_764;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Landlord {
	@Id Long id;
	private String name;
	private ArrayList<Long> buildings;
	private String email;
	
	private Landlord(){
		
	}
	public Landlord(String name, String email ){
		this.name=name;
		this.email=email;
		this.buildings=new ArrayList<Long>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Long> getBuildings() {
		return buildings;
	}
	public void setBuildings(ArrayList<Long> buildings) {
		this.buildings = buildings;
	}
	
	public void addBuilding(Long building){
		this.buildings.add(building);
	}
	
	public void deleteBuilding(Long building){
		this.buildings.remove(building);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
}