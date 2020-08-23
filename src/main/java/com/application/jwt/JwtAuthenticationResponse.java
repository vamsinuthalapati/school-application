package com.application.jwt;

public class JwtAuthenticationResponse {

	private String accessToken;
	private String tokenType;
	private String email;
	private String firstName;
	private String lastName;
	private String type;
	private String externalId;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public JwtAuthenticationResponse(String accessToken, String tokenType, String email, String firstName,
			String lastName, String type, String externalId) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.externalId = externalId;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", type=" + type + ", externalId="
				+ externalId + "]";
	}

}
