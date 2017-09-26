package com.ntt.model;

public class Charger {
	private int chargerId;
	private String userName;
	private String password;

	public Charger() {

	}

	public Charger(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public int getChargerId() {
		return chargerId;
	}

	public void setChargerId(int chargerId) {
		this.chargerId = chargerId;
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
