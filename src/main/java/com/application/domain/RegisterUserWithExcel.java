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
	@JsonProperty(value = "stream")
	private String stream;
	@JsonProperty(value = "semester")
	private String semester;
	@JsonProperty(value = "year")
	private String year;

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

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public RegisterUserWithExcel(String email, String firstName, String lastName, String type, String stream,
			String semester, String year) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.stream = stream;
		this.semester = semester;
		this.year = year;
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
				+ ", type=" + type + ", stream=" + stream + ", semester=" + semester + ", year=" + year + "]";
	}

}
