package com.example.app.domain.user.dto;

public class Session {
	private int sessionId;
	private String userId;
	private String role;
	
	public Session() {
		// TODO Auto-generated constructor stub
	}

	public Session(int sessionId, String userId, String role) {
		super();
		this.sessionId = sessionId;
		this.userId = userId;
		this.role = role;
	}

	public Session(String userId, String role) {
		super();
		this.userId = userId;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Session [sessionId=" + sessionId + ", userId=" + userId + ", role=" + role + "]";
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
