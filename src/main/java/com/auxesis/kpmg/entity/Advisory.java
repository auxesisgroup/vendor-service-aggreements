package com.auxesis.kpmg.entity;

import java.math.BigInteger;

public class Advisory {

	private BigInteger msl;
	private BigInteger esl;
	private BigInteger usl;
	private BigInteger reward;
	private BigInteger panalty;
	private BigInteger cutOff;
	private BigInteger rolling;
	private BigInteger creditTerm;
	private BigInteger clientApprovalStatus;
	private BigInteger vendorApprovalStatus;

	public BigInteger getClientApprovalStatus() {
		return clientApprovalStatus;
	}

	public void setClientApprovalStatus(BigInteger clientApprovalStatus) {
		this.clientApprovalStatus = clientApprovalStatus;
	}

	public BigInteger getVendorApprovalStatus() {
		return vendorApprovalStatus;
	}

	public void setVendorApprovalStatus(BigInteger vendorApprovalStatus) {
		this.vendorApprovalStatus = vendorApprovalStatus;
	}

	public BigInteger getCreditTerm() {
		return creditTerm;
	}

	public void setCreditTerm(BigInteger creditTerm) {
		this.creditTerm = creditTerm;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	private String contractAddress;

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

	@Override
	public String toString() {
		return "Advisory [msl=" + msl + ", esl=" + esl + ", usl=" + usl + ", reward=" + reward + ", panalty=" + panalty
				+ ", cutOff=" + cutOff + ", rolling=" + rolling + ", contractAddress=" + contractAddress + "]";
	}

}
