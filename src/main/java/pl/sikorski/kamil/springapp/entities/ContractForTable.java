package pl.sikorski.kamil.springapp.entities;

import java.sql.Date;

public class ContractForTable {
	private long id;
	private boolean active;
	private double amount;
	private String amountPeriod;
	private String amountType;
	private double authorizationPercent;
	private Date fromDate;
	private String orderNumber;
	private Double request;
	private Date toDate;
	private long systemId;

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

	public long getSystemId() {
		return systemId;
	}

	public void setSystemId(long systemId) {
		this.systemId = systemId;
	}

}
