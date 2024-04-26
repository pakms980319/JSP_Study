package com.example.app.domain.user.dto;

public class BussinessMan {
	private String bussinessManId;
	private String userId;

	public BussinessMan() {}

	public BussinessMan(String bussinessManId, String userId) {
		super();
		this.bussinessManId = bussinessManId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BussinessMan [bussinessManId=" + bussinessManId + ", userId=" + userId + "]";
	}

	public String getBussinessManId() {
		return bussinessManId;
	}

	public void setBussinessManId(String bussinessManId) {
		this.bussinessManId = bussinessManId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
