package com.appspot.t_gecko_764;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MaintenanceRequest {
	private String name;
	private String priority;
	private String location;
	private String details;
	private Calendar dateCreated;
	private Person owner;
	private Group group;
	
<<<<<<< HEAD


	public MaintenanceRequest(String priority, String location, String details){
		this.priority=priority;
		this.location=location;
		this.details=details;
		
	}

	private MaintenanceRequest(){}




=======
	private MaintenanceRequest(){}
>>>>>>> 28b81a7a423566ceb136f7f8b53274fb9cc52416
	
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
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
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
		private Calendar dateCreated;
		private Person owner;
		private Group group;
		
		public Builder(String name, Person owner, Group group){
			this.name = name;
			this.owner = owner;
			this.group = group;
			this.dateCreated = new GregorianCalendar();
		}
		
		public MaintenanceRequest build(){
			MaintenanceRequest request = new MaintenanceRequest();
			request.setName(name);
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

		public Builder setDateCreated(Calendar dateCreated) {
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