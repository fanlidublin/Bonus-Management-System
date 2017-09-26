package com.ntt.model;

public class Staff {
	private int staffId;
	private String userName;
	private String password;

	public Staff() {

	}

	public Staff(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
