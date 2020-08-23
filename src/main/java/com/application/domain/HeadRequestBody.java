package com.application.domain;

public class HeadRequestBody {

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String contactNumber;
	private String countryCode;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public HeadRequestBody(String email, String firstName, String lastName, String password, String contactNumber,
			String countryCode) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.contactNumber = contactNumber;
		this.countryCode = countryCode;
	}

	public HeadRequestBody() {
		super();
	}

	@Override
	public String toString() {
		return "HeadRequestBody [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", contactNumber=" + contactNumber + ", countryCode=" + countryCode + "]";
	}

}
