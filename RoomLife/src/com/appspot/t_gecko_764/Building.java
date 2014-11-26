package com.appspot.t_gecko_764;

import java.util.ArrayList;

public class Building {
	private String name;
	private String landlord;
	private ArrayList<Group> apts;
	
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
	public ArrayList<Group> getApts() {
		return apts;
	}
	public void setApts(ArrayList<Group> apts) {
		this.apts = apts;
	}
	
}