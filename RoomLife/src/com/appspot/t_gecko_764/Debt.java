package com.appspot.t_gecko_764;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Debt implements Comparable{
	@Id private Long id;
	private String name;
	private Double amount;
	@Index private String owner;
	@Index private String debtor;
	private Date dateCreated;
	private Date datePaid;
	private Long billId;
	
	
	private Debt(){}
	
	@Override
	public int compareTo(Object o) {
		Debt d = (Debt) o;
		return dateCreated.compareTo(d.getDateCreated());
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getDebtor() {
		return debtor;
	}
	public void setDebtor(String debtor) {
		this.debtor = debtor;
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
		private Double amount;
		private String owner;
		private String debtor;
		private Date dateCreated;
		private Date datePaid;
		private Long billId;
		
		public Builder(String name, Double amount, Person owner, Person debtor) {
			this.name = name;
			this.amount = amount;
			this.owner = owner.getEmail();
			this.debtor = debtor.getEmail();
			this.dateCreated = new Date();
		}
		
		public Debt build(){
			Debt debt = new Debt();
			debt.setName(name);
			debt.setAmount(amount);
			debt.setOwner(owner);
			debt.setDebtor(debtor);
			debt.setDateCreated(dateCreated);
			debt.setDatePaid(datePaid);
			debt.setBillId(billId);
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
			this.owner = owner.getEmail();
			return this;
		}

		public Builder setDebtor(Person debtor) {
			this.debtor = debtor.getEmail();
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
		
		public Builder setBillId(Long billId) {
			this.billId = billId;
			return this;
		}

	}
	
}