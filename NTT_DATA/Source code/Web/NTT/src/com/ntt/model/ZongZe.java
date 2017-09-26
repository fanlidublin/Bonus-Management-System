package com.ntt.model;

/**
 * @author LF
 *
 */
public class ZongZe {

	private int centerId;
	private String centerName;
	private String bpRate;
	private String projectRateUp;
	private String projectRateDown;
	private String shareBonusRateUp;
	private String shareBonusRateDown;
	private String tuijianRate;

	public ZongZe() {

	}

	public ZongZe(String centerName, String bpRate, String projectRateUp, String projectRateDown,
			String shareBonusRateUp, String shareBonusRateDown, String tuijianRate) {
		this.centerName = centerName;
		this.bpRate = bpRate;
		this.projectRateUp = projectRateUp;
		this.projectRateDown = projectRateDown;
		this.shareBonusRateUp = shareBonusRateUp;
		this.shareBonusRateDown = shareBonusRateDown;
		this.tuijianRate = tuijianRate;
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

	public String getBpRate() {
		return bpRate;
	}

	public void setBpRate(String bpRate) {
		this.bpRate = bpRate;
	}

	public String getProjectRateUp() {
		return projectRateUp;
	}

	public void setProjectRateUp(String projectRateUp) {
		this.projectRateUp = projectRateUp;
	}

	public String getProjectRateDown() {
		return projectRateDown;
	}

	public void setProjectRateDown(String projectRateDown) {
		this.projectRateDown = projectRateDown;
	}

	public String getShareBonusRateUp() {
		return shareBonusRateUp;
	}

	public void setShareBonusRateUp(String shareBonusRateUp) {
		this.shareBonusRateUp = shareBonusRateUp;
	}

	public String getShareBonusRateDown() {
		return shareBonusRateDown;
	}

	public String getTuijianRate() {
		return tuijianRate;
	}

	public void setTuijianRate(String tuijianRate) {
		this.tuijianRate = tuijianRate;
	}

	public void setShareBonusRateDown(String shareBonusRateDown) {
		this.shareBonusRateDown = shareBonusRateDown;
	}

}