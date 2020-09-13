package com.application.domain;

public class UserInfo {

	private String email;
	private String firstName;
	private String lastName;
	private String type;
	private String accessToken;
	private String expiryTime;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiryTime() {
		return expiryTime;
	}

	public void setExpiryTime(String expiryTime) {
		this.expiryTime = expiryTime;
	}

	public UserInfo(String email, String firstName, String lastName, String type, String accessToken,
			String expiryTime) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.accessToken = accessToken;
		this.expiryTime = expiryTime;
	}

	public UserInfo() {
		super();
	}

	@Override
	public String toString() {
		return "UserInfo [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type
				+ ", accessToken=" + accessToken + ", expiryTime=" + expiryTime + "]";
	}

}
