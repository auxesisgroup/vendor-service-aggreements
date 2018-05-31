package com.auxesis.kpmg.entity;

import java.math.BigInteger;

public class Performance {
	private int month;
	private int performance;
	private String contractAddress;
	private BigInteger expectedPayout;

	public BigInteger getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(BigInteger timestamp) {
		this.timestamp = timestamp;
	}

	private BigInteger timestamp;

	public BigInteger getExpectedPayout() {
		return expectedPayout;
	}

	public void setExpectedPayout(BigInteger expectedPayout) {
		this.expectedPayout = expectedPayout;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getPerformance() {
		return performance;
	}

	public void setPerformance(int perfomance) {
		this.performance = perfomance;
	}
}
