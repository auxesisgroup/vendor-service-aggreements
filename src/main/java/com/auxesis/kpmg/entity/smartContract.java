package com.auxesis.kpmg.entity;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class smartContract {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "services")
	private String services;

	@Column(name = "vendor")
	private String vendor;

	@Column(name = "sub_service_type")
	private String subServiceType;

	@Column(name = "budget")
	private BigInteger budget;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "budget_per_month")
	private BigInteger budgetPerMonth;

	@Column(name = "po_amount")
	private BigInteger poAmount;

	@Column(name = "po_id")
	private String poId;

	@Column(name = "msl")
	private BigInteger msl;

	@Column(name = "esl")
	private BigInteger esl;

	@Column(name = "usl")
	private BigInteger usl;

	@Column(name = "reward")
	private BigInteger reward;

	@Column(name = "panalty")
	private BigInteger panalty;

	@Column(name = "cut_off")
	private BigInteger cutOff;

	@Column(name = "rolling")
	private BigInteger rolling;

	@Column(name = "contract_address")
	private String contractAddress;

	@Column(name = "vendor_id")
	private String vendorId;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "advisory_id")
	private String advisoryId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAdvisoryId() {
		return advisoryId;
	}

	public void setAdvisoryId(String advisoryId) {
		this.advisoryId = advisoryId;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractAddress() {
		return contractAddress;
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

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
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

	public BigInteger getMsl() {
		return msl;
	}

	public void setMsl(BigInteger msl) {
		this.msl = msl;
	}

	public BigInteger getEsl() {
		return esl;
	}

	public void setEsl(BigInteger esl) {
		this.esl = esl;
	}

	public BigInteger getUsl() {
		return usl;
	}

	public void setUsl(BigInteger usl) {
		this.usl = usl;
	}

	public BigInteger getReward() {
		return reward;
	}

	public void setReward(BigInteger reward) {
		this.reward = reward;
	}

	public BigInteger getPanalty() {
		return panalty;
	}

	public void setPanalty(BigInteger panalty) {
		this.panalty = panalty;
	}

	public BigInteger getCutOff() {
		return cutOff;
	}

	public void setCutOff(BigInteger cutOff) {
		this.cutOff = cutOff;
	}

	public BigInteger getRolling() {
		return rolling;
	}

	public void setRolling(BigInteger rolling) {
		this.rolling = rolling;
	}
}
