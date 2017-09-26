package com.ntt.model;

public class BonusNorm {
	private int centerId;
	private String centerName;
	private String zhengshiNorm;
	private String bpNorm;
	private String lianxieNorm;
	private String tuizhiNorm;

	public BonusNorm() {

	}

	public BonusNorm(String centerName, String zhengshiNorm, String bpNorm, String lianxieNorm, String tuizhiNorm) {
		this.centerName = centerName;
		this.zhengshiNorm = zhengshiNorm;
		this.bpNorm = bpNorm;
		this.lianxieNorm = lianxieNorm;
		this.tuizhiNorm = tuizhiNorm;
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

	public String getZhengshiNorm() {
		return zhengshiNorm;
	}

	public void setZhengshiNorm(String zhengshiNorm) {
		this.zhengshiNorm = zhengshiNorm;
	}

	public String getBpNorm() {
		return bpNorm;
	}

	public void setBpNorm(String bpNorm) {
		this.bpNorm = bpNorm;
	}

	public String getLianxieNorm() {
		return lianxieNorm;
	}

	public void setLianxieNorm(String lianxieNorm) {
		this.lianxieNorm = lianxieNorm;
	}

	public String getTuizhiNorm() {
		return tuizhiNorm;
	}

	public void setTuizhiNorm(String tuizhiNorm) {
		this.tuizhiNorm = tuizhiNorm;
	}

}
