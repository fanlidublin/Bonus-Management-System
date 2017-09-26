package com.ntt.model;



public class xiZeModify2 {
	private int projectId;
	private String name;
	private int centerId;
	private String centerName;
	private int bonustotal;
	private String zhongxin;
	private String benbu;
	private String bumen;
	private String pm;
	
	public xiZeModify2() {

	}

	public xiZeModify2(int projectId,String name,int centerId,String centerName,int bonustotal,String zhongxin,String benbu,String bumen,String pm) {
		this.projectId = projectId;
		this.name = name;
		this.bonustotal = bonustotal;
		this.centerId = centerId;
		this.centerName = centerName;
		this.zhongxin = zhongxin;
		this.benbu = benbu;
		this.bumen = bumen;
		this.pm = pm;	
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

	public String getZhongxin() {
		return zhongxin;
	}

	public void setZhongxin(String zhongxin) {
		this.zhongxin = zhongxin;
	}

	public String getBenbu() {
		return benbu;
	}

	public void setBenbu(String benbu) {
		this.benbu = benbu;
	}

	public String getBumen() {
		return bumen;
	}

	public void setBumen(String bumen) {
		this.bumen = bumen;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}



	
}
