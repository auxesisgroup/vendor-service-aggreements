package com.auxesis.kpmg.entity;

import java.math.BigInteger;
import java.sql.Date;

public class Contract extends Advisory {

	private String id;

	private String services;
	private String vendor;
	private String subServiceType;
	private BigInteger budget;
	private Date startDate;
	private Date endDate;
	private BigInteger budgetPerMonth;
	private BigInteger poAmount;
	private String poId;
	private String clientId;
	private BigInteger timestamp;
	public String getId() {
		return id;
	}

	public BigInteger getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigInteger timestamp) {
		this.timestamp = timestamp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getSubServiceType() {
		return subServiceType;
	}

	public void setSubServiceType(String subServiceType) {
		this.subServiceType = subServiceType;
	}

	public BigInteger getBudget() {
		return budget;
	}

	public void setBudget(BigInteger budget) {
		this.budget = budget;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigInteger getBudgetPerMonth() {
		return budgetPerMonth;
	}

	public void setBudgetPerMonth(BigInteger budgetPerMonth) {
		this.budgetPerMonth = budgetPerMonth;
	}

	public BigInteger getPoAmount() {
		return poAmount;
	}

	public void setPoAmount(BigInteger poAmount) {
		this.poAmount = poAmount;
	}

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	@Override
	public String toString() {
		return "Contract [services=" + services + ", vendor=" + vendor + ", subServiceType=" + subServiceType
				+ ", budget=" + budget + ", startDate=" + startDate + ", endDate=" + endDate + ", budgetPerMonth="
				+ budgetPerMonth + ", poAmount=" + poAmount + ", poId=" + poId + "]";
	}

}
