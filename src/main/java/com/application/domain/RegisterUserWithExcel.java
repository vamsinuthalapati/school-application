package com.application.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserWithExcel {

	@JsonProperty(value = "email")
	private String email;
	@JsonProperty(value = "firstName")
	private String firstName;
	@JsonProperty(value = "lastName")
	private String lastName;
	@JsonProperty(value = "type")
	private String type;

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

	public RegisterUserWithExcel(String email, String firstName, String lastName, String type) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
	}

	public RegisterUserWithExcel() {
		super();
	}

	@Override
	public boolean equals(Object anObject) {
		if (!(anObject instanceof RegisterUserWithExcel)) {
			return false;
		}
		RegisterUserWithExcel otherMember = (RegisterUserWithExcel) anObject;
		return otherMember.getEmail().equals(getEmail());
	}

	@Override
	public String toString() {
		return "RegisterUserWithExcel [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", type=" + type + "]";
	}

}