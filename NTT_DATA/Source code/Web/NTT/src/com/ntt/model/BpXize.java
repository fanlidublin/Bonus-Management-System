package com.ntt.model;

public class BpXize {

	private int centerId;
	private String centerName;
	private String zxFund;
	private String bbFund;
	private String bmFund;
	private String xmFund;
	private String sumFund;

	public BpXize() {

	}

	public BpXize(String centerName, String zxFund, String bbFund, String bmFund, String xmFund, String sumFund) {
		super();
		this.centerName = centerName;
		this.zxFund = zxFund;
		this.bbFund = bbFund;
		this.bmFund = bmFund;
		this.xmFund = xmFund;
		this.sumFund = sumFund;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getZxFund() {
		return zxFund;
	}

	public void setZxFund(String zxFund) {
		this.zxFund = zxFund;
	}

	public String getBbFund() {
		return bbFund;
	}

	public void setBbFund(String bbFund) {
		this.bbFund = bbFund;
	}

	public String getBmFund() {
		return bmFund;
	}

	public void setBmFund(String bmFund) {
		this.bmFund = bmFund;
	}

	public String getXmFund() {
		return xmFund;
	}

	public void setXmFund(String xmFund) {
		this.xmFund = xmFund;
	}

	public String getSumFund() {
		return sumFund;
	}

	public void setSumFund(String sumFund) {
		this.sumFund = sumFund;
	}

}
