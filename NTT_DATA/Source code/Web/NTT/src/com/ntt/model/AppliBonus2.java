package com.ntt.model;

import java.util.Date;

public class AppliBonus2 {
	private int projectId;
	private String financialId;
	private String projectName;
	private int employId;
	private String employName;
	private int depId;
	private String depName;
	private Double zhengshiMonth;
	private Double BPMonth;
	private Double lianxieMonth;
	private Double lizhiMonth;
	private String applyDate;
	private Date applyDate2;
	private String bonustotal;
	private String bonushuafen;
	private int centerId;
	
	public AppliBonus2() {

	}

	public AppliBonus2(int projectId,String financialId,String projectName,int employId,String employName,int depId,String depName, Double zhengshiMonth, Double BPMonth
			, Double lianxieMonth, Double lizhiMonth,String applyDate,String bonustotal,String bonushuafen,Date applyDate2,int centerId) {
		this.projectId = projectId;
		this.financialId = financialId;
		this.projectName = projectName;
		this.employId = employId;
		this.employName = employName;
		this.depId = depId;
		this.depName = depName;
		this.zhengshiMonth = zhengshiMonth;
		this.BPMonth = BPMonth;
		this.lianxieMonth = lianxieMonth;
		this.lizhiMonth = lizhiMonth;
		this.applyDate = applyDate;
		this.applyDate2 = applyDate2;
		this.bonustotal = bonustotal;
		this.bonushuafen = bonushuafen;
		this.centerId = centerId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getFinancialId() {
		return financialId;
	}

	public void setFinancialId(String financialId) {
		this.financialId = financialId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getEmployId() {
		return employId;
	}

	public void setEmployId(int employId) {
		this.employId = employId;
	}

	public String getEmployName() {
		return employName;
	}

	public void setEmployName(String employName) {
		this.employName = employName;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Double getZhengshiMonth() {
		return zhengshiMonth;
	}

	public void setZhengshiMonth(Double zhengshiMonth) {
		this.zhengshiMonth = zhengshiMonth;
	}

	public Double getBPMonth() {
		return BPMonth;
	}

	public void setBPMonth(Double bPMonth) {
		BPMonth = bPMonth;
	}

	public Double getLianxieMonth() {
		return lianxieMonth;
	}

	public void setLianxieMonth(Double lianxieMonth) {
		this.lianxieMonth = lianxieMonth;
	}

	public Double getLizhiMonth() {
		return lizhiMonth;
	}

	public void setLizhiMonth(Double lizhiMonth) {
		this.lizhiMonth = lizhiMonth;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public Date getApplyDate2() {
		return applyDate2;
	}

	public void setApplyDate2(Date applyDate2) {
		this.applyDate2 = applyDate2;
	}

	public String getBonustotal() {
		return bonustotal;
	}

	public void setBonustotal(String bonustotal) {
		this.bonustotal = bonustotal;
	}

	public String getBonushuafen() {
		return bonushuafen;
	}

	public void setBonushuafen(String bonushuafen) {
		this.bonushuafen = bonushuafen;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

}
