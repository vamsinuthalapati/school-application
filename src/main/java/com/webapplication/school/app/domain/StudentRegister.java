package com.webapplication.school.app.domain;

import java.util.Calendar;

public class StudentRegister {

	private String rollNumber;
	private String name;
	private Calendar dateOfBirth;
	private String password;
	private String contactNumber;
	private String email;
	private String gender;
	private String className;
	private String section;
	public String getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public StudentRegister(String rollNumber, String name, Calendar dateOfBirth, String password, String contactNumber,
			String email, String gender, String className, String section) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.contactNumber = contactNumber;
		this.email = email;
		this.gender = gender;
		this.className = className;
		this.section = section;
	}
	public StudentRegister() {
		super();
	}
	@Override
	public String toString() {
		return "StudentRegister [rollNumber=" + rollNumber + ", name=" + name + ", dateOfBirth=" + dateOfBirth
				+ ", password=" + password + ", contactNumber=" + contactNumber + ", email=" + email + ", gender="
				+ gender + ", className=" + className + ", section=" + section + "]";
	}
	
	
	
}
