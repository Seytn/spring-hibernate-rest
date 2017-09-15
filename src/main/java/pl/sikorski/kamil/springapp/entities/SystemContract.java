package pl.sikorski.kamil.springapp.entities;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "System_Contract", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"request", "system_id"})
	}) 
public class SystemContract {
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY )
	@Column(name = "id", columnDefinition = "bigint NOT NULL")
	private long id;
	@Column(name = "active", columnDefinition = "boolean")
	private boolean active;
	@Column(name = "amount", columnDefinition = "numeric(19,2)")
	private double amount;
	@Column(name = "amount_period", columnDefinition = "character varying(5)")
	private String amountPeriod;
	@Column(name = "amount_type", columnDefinition = "character varying(5)")
	private String amountType;
	@Column(name = "authorization_percent", columnDefinition = "numeric(19,2)")
	private double authorizationPercent;
	@Column(name = "from_date", columnDefinition = "date")
	private Date fromDate;
	@Column(name = "order_number", columnDefinition = "character varying(12)")
	private String orderNumber;
	@Column(name = "request", columnDefinition = "character varying(12)")
	private double request;
	@Column(name = "to_date", columnDefinition = "date")
	private Date toDate;
	
	@ManyToOne
	@JoinColumn(name = "system_id")
	private System system;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAmountPeriod() {
		return amountPeriod;
	}

	public void setAmountPeriod(String amountPeriod) {
		this.amountPeriod = amountPeriod;
	}

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public double getAuthorizationPercent() {
		return authorizationPercent;
	}

	public void setAuthorizationPercent(double authorizationPercent) {
		this.authorizationPercent = authorizationPercent;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getRequest() {
		return request;
	}

	public void setRequest(double d) {
		this.request = d;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public System getSystem() {
		return system;
	}

	public void setSystem(System system) {
		this.system = system;
	}




	

}
