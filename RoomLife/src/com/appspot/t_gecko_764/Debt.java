package cmcbride.utexas.edu;

import java.util.ArrayList;
import java.util.Date;

public class Debt {
	private String name;
	private Double amount;
	private Person owner;
	private Person debtor;
	private Date dateDeadline;
	private Date dateCreated;
	private Date datePaid;
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