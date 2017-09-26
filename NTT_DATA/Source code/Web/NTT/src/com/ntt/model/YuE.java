package com.ntt.model;

public class YuE {
	private int centerId;
	private String centerName;
	private String zxFund;
	private String bbFund;
	private String bmFund;
	private String xmFund;
	private String sumFund;

	public YuE() {

	}

	public YuE(String centerName, String zxFund, String bbFund, String bmFund) {
		super();
		this.centerName = centerName;
		this.zxFund = zxFund;
		this.bbFund = bbFund;
		this.bmFund = bmFund;
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
