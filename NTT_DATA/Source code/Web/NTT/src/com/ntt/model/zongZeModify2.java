package com.ntt.model;



public class zongZeModify2 {
	private int projectId;
	private String name;
	private int centerId;
	private String centerName;
	private int bonustotal;
	private double proMoneyRatioMax;
	private double proMoneyRatioMin;
	private double proBonusRatioMin;
	private String proMoneyMax;
	private String proMoneyMin;
	private String proBonusMin;
	private String zhongxinMoney;
	
	public zongZeModify2() {

	}

	public zongZeModify2(int projectId,String name,int centerId,String centerName,int bonustotal,double proMoneyRatioMax,double proMoneyRatioMin,double proBonusRatioMin,String proMoneyMax,String proMoneyMin,String proBonusMin,String zhongxinMoney) {
		this.projectId = projectId;
		this.name = name;
		this.bonustotal = bonustotal;
		this.centerId = centerId;
		this.centerName = centerName;
		this.proMoneyRatioMax = proMoneyRatioMax;
		this.proMoneyRatioMin = proMoneyRatioMin;
		this.proBonusRatioMin = proBonusRatioMin;
		this.proMoneyMax = proMoneyMax;
		this.proMoneyMin = proMoneyMin;
		this.proBonusMin = proBonusMin;
		this.zhongxinMoney = zhongxinMoney;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getBonustotal() {
		return bonustotal;
	}

	public void setBonustotal(int bonustotal) {
		this.bonustotal = bonustotal;
	}

	public double getProMoneyRatioMax() {
		return proMoneyRatioMax;
	}

	public void setProMoneyRatioMax(double proMoneyRatioMax) {
		this.proMoneyRatioMax = proMoneyRatioMax;
	}

	public double getProMoneyRatioMin() {
		return proMoneyRatioMin;
	}

	public void setProMoneyRatioMin(double proMoneyRatioMin) {
		this.proMoneyRatioMin = proMoneyRatioMin;
	}

	public double getProBonusRatioMin() {
		return proBonusRatioMin;
	}

	public void setProBonusRatioMin(double proBonusRatioMin) {
		this.proBonusRatioMin = proBonusRatioMin;
	}

	public String getProMoneyMax() {
		return proMoneyMax;
	}

	public void setProMoneyMax(String proMoneyMax) {
		this.proMoneyMax = proMoneyMax;
	}

	public String getProMoneyMin() {
		return proMoneyMin;
	}

	public void setProMoneyMin(String proMoneyMin) {
		this.proMoneyMin = proMoneyMin;
	}

	public String getProBonusMin() {
		return proBonusMin;
	}

	public void setProBonusMin(String proBonusMin) {
		this.proBonusMin = proBonusMin;
	}

	public String getZhongxinMoney() {
		return zhongxinMoney;
	}

	public void setZhongxinMoney(String zhongxinMoney) {
		this.zhongxinMoney = zhongxinMoney;
	}

	
}
