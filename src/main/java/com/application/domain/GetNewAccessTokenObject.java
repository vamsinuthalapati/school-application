package com.application.domain;

public class GetNewAccessTokenObject {

	private String accessToken;
	private String expiryTime;

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

	public GetNewAccessTokenObject(String accessToken, String expiryTime) {
		super();
		this.accessToken = accessToken;
		this.expiryTime = expiryTime;
	}

	public GetNewAccessTokenObject() {
		super();
	}

	@Override
	public String toString() {
		return "GetNewAccessTokenObject [accessToken=" + accessToken + ", expiryTime=" + expiryTime + "]";
	}

}
