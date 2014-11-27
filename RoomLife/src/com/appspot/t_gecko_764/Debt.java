package com.appspot.t_gecko_764;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Debt {
	@Id private Long id;
	private String name;
	private Double amount;
	private Person owner;
	private Person debtor;
	private long dateCreated;
	private Date datePaid;
	
	public Debt(String name, Double amount, Person owner, Person debtor, long dateCreated) {
		this.name = name;
		this.amount = amount;
		this.owner = owner;
		this.debtor = debtor;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public Person getDebtor() {
		return debtor;
	}
	public void setDebtor(Person debtor) {
		this.debtor = debtor;
	}
	public long getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDatePaid() {
		return datePaid;
	}
	public void setDatePaid(Date datePaid) {
		this.datePaid = datePaid;
	}
	
}