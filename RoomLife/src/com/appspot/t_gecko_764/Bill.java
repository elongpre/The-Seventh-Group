package com.appspot.t_gecko_764;

import java.util.ArrayList;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Bill {
	@Id private Long id;
	private String name;
	private double amount;
	private Person owner;
	private Group group;
	private ArrayList<Person> peeps;
	private Date dateDeadline;
	private Date dateCreated;
	private Date datePaid;
	
	private Bill(){}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public ArrayList<Person> getPeeps() {
		return peeps;
	}
	public void setPeeps(ArrayList<Person> peeps) {
		this.peeps = peeps;
	}
	public Date getDateDeadline() {
		return dateDeadline;
	}
	public void setDateDeadline(Date dateDeadline) {
		this.dateDeadline = dateDeadline;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
	
	public static class Builder{
		private String name;
		private double amount;
		private Person owner;
		private Group group;
		private ArrayList<Person> peeps;
		private Date dateDeadline;
		private Date dateCreated;
		private Date datePaid;
		
		public Builder(String name, Double amount, Person owner) {
			this.name = name;
			this.amount = amount;
			this.owner = owner;
			this.dateCreated = new Date();
		}	
		
		public Bill build(){
			Bill bill = new Bill();
			bill.setName(name);
			bill.setAmount(amount);
			bill.setOwner(owner);
			bill.setGroup(group);
			bill.setPeeps(peeps);
			bill.setDateDeadline(dateDeadline);
			bill.setDateCreated(dateCreated);
			bill.setDatePaid(datePaid);
			return bill;			
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAmount(double amount) {
			this.amount = amount;
			return this;
		}

		public Builder setOwner(Person owner) {
			this.owner = owner;
			return this;
		}

		public Builder setGroup(Group group) {
			this.group = group;
			this.peeps = new ArrayList<Person>();
			for(Person person : group.getMembers()){
				this.peeps.add(person);
			}
			return this;
		}

		public Builder setPeeps(ArrayList<Person> peeps) {
			this.peeps = peeps;
			return this;
		}

		public Builder setDateDeadline(Date dateDeadline) {
			this.dateDeadline = dateDeadline;
			return this;
		}

		public Builder setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
			return this;
		}

		public Builder setDatePaid(Date datePaid) {
			this.datePaid = datePaid;
			return this;
		}

		
	}	
}