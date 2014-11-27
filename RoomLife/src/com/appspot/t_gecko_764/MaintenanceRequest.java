package com.appspot.t_gecko_764;

import java.util.ArrayList;
import java.util.Date;

public class MaintenanceRequest {
	//private String name;
	private String priority;
	private String location;
	private String details;
	private Date dateCreated;
	private Person owner;
	private Group group;
	
	private MaintenanceRequest(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public static class Builder{
		private String name;
		private String priority = "low";
		private String location = "unnammed";
		private String details = "no details";
		private Date dateCreated;
		private Person owner;
		private Group group;
		
		public Builder(String name, Person owner, Group group){
			this.name = name;
			this.owner = owner;
			this.group = group;
			this.dateCreated = new Date();
		}
		
		public MaintenanceRequest build(){
			MaintenanceRequest request = new MaintenanceRequest();
			request.setName(this.name);
			request.setPriority(this.priority);
			request.setLocation(this.location);
			request.setDetails(this.details);
			request.setDateCreated(this.dateCreated);
			request.setOwner(this.owner);
			request.setGroup(this.group);
			return request;
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setPriority(String priority) {
			this.priority = priority;
			return this;
		}

		public Builder setLocation(String location) {
			this.location = location;
			return this;
		}

		public Builder setDetails(String details) {
			this.details = details;
			return this;
		}

		public Builder setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
			return this;
		}

		public Builder setOwner(Person owner) {
			this.owner = owner;
			return this;
		}

		public Builder setGroup(Group group) {
			this.group = group;
			return this;
		}

		
		
		
		
	}
	
	
}