package com.ntt.model;

import java.util.Date;

public class AppliBonus {
	private int projectId;
	private String financialId;
	private String projectName;
	private Double orderPerformance;
	private Double requestPerformance;
	private Double supportPerformance;
	private Date projectStartDate;
	private Date projectEndDate;
	private int centerId;

	public AppliBonus() {

	}

	public AppliBonus(String financialId, String projectName, Double orderPerformance, Double requestPerformance
			, Double supportPerformance, Date projectStartDate, Date projectEndDate) {
		this.projectId = projectId;
		this.financialId = financialId;
		this.projectName = projectName;
		this.orderPerformance = orderPerformance;
		this.requestPerformance = requestPerformance;
		this.supportPerformance = supportPerformance;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
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

	public Double getOrderPerformance() {
		return orderPerformance;
	}

	public void setOrderPerformance(Double orderPerformance) {
		this.orderPerformance = orderPerformance;
	}

	public Double getRequestPerformance() {
		return requestPerformance;
	}

	public void setRequestPerformance(Double requestPerformance) {
		this.requestPerformance = requestPerformance;
	}

	public Double getSupportPerformance() {
		return supportPerformance;
	}

	public void setSupportPerformance(Double supportPerformance) {
		this.supportPerformance = supportPerformance;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

}
