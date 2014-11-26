package com.appspot.t_gecko_764;

import java.util.ArrayList;
import java.util.Date;

public class Bill {
	private String name;
	private double amount;
	private Person owner;
	private Group group;
	private ArrayList<Person> peeps;
	private Date dateDeadline;
	private Date dateCreated;
	private Date datePaid;
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
	
	
}