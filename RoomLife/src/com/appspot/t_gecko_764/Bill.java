package com.appspot.t_gecko_764;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Bill {
	@Id private Long id;
	private String name;
	private double amount;
	@Index private Person owner;
	private Group group;
	private ArrayList<Person> peeps;
	private Calendar dateDeadline;
	private Calendar dateCreated;
	private Calendar datePaid;
	
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
	public Calendar getDateDeadline() {
		return dateDeadline;
	}
	public void setDateDeadline(Calendar dateDeadline) {
		this.dateDeadline = dateDeadline;
	}
	public Calendar getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Calendar dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Calendar getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Calendar datePaid) {
		this.datePaid = datePaid;
	}
	
	public static class Builder{
		private String name;
		private double amount;
		private Person owner;
		private Group group;
		private ArrayList<Person> peeps;
		private Calendar dateDeadline;
		private Calendar dateCreated;
		private Calendar datePaid;
		
		public Builder(String name, Double amount, Person owner) {
			this.name = name;
			this.amount = amount;
			this.owner = owner;
			this.dateCreated = new GregorianCalendar();
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

		public Builder setDateDeadline(Calendar dateDeadline) {
			this.dateDeadline = dateDeadline;
			return this;
		}

		public Builder setDateCreated(Calendar dateCreated) {
			this.dateCreated = dateCreated;
			return this;
		}

		public Builder setDatePaid(Calendar datePaid) {
			this.datePaid = datePaid;
			return this;
		}

		
	}	
}