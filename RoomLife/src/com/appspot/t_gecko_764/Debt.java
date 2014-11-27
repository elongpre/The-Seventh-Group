package com.appspot.t_gecko_764;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Debt {
	@Id private Long id;
	private String name;
	private Double amount;
	private Person owner;
	private Person debtor;
	private Calendar dateCreated;
	private Calendar datePaid;
	
	private Debt(){}
	
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
		private Double amount;
		private Person owner;
		private Person debtor;
		private Calendar dateCreated;
		private Calendar datePaid;
		
		public Builder(String name, Double amount, Person owner, Person debtor) {
			this.name = name;
			this.amount = amount;
			this.owner = owner;
			this.debtor = debtor;
			this.dateCreated = new GregorianCalendar();
		}
		
		public Debt build(){
			Debt debt = new Debt();
			debt.setName(name);
			debt.setAmount(amount);
			debt.setOwner(owner);
			debt.setDebtor(debtor);
			debt.setDateCreated(dateCreated);
			debt.setDatePaid(datePaid);
			return debt;
		}
		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAmount(Double amount) {
			this.amount = amount;
			return this;
		}

		public Builder setOwner(Person owner) {
			this.owner = owner;
			return this;
		}

		public Builder setDebtor(Person debtor) {
			this.debtor = debtor;
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