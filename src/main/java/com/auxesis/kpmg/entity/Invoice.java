package com.auxesis.kpmg.entity;

import java.math.BigInteger;

import org.web3j.abi.datatypes.Bool;

public class Invoice {
	private String invoiceId;
	private BigInteger invoiceAmount;
	private BigInteger payout;
	private int month;
	private int status;
	private Boolean cutOff;
	private int reward;
	private int penalty;
	private int performance;
	private int payoutStatus;

	public int getPayoutStatus() {
		return payoutStatus;
	}

	public void setPayoutStatus(int payoutStatus) {
		this.payoutStatus = payoutStatus;
	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int performance) {
		this.performance = performance;
	}

	public Boolean getCutOff() {
		return cutOff;
	}

	public void setCutOff(Boolean cutOff) {
		this.cutOff = cutOff;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	private BigInteger timestamp;

	public BigInteger getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigInteger timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	private String contractAddress;

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public BigInteger getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(BigInteger invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public BigInteger getPayout() {
		return payout;
	}

	public void setPayout(BigInteger payout) {
		this.payout = payout;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

}
