package com.webapplication.school.app.domain;

import java.util.Calendar;

public class TeacherRegister {

	private String employeeId;
	private String name;
	private Calendar dateOfBirth;
	private String password;
	private String contactNumber;
	private String email;
	private String gender;
	private String className;
	private String section;
	private String subject;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public TeacherRegister(String employeeId, String name, Calendar dateOfBirth, String password, String contactNumber,
			String email, String gender, String className, String section, String subject) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.contactNumber = contactNumber;
		this.email = email;
		this.gender = gender;
		this.className = className;
		this.section = section;
		this.subject = subject;
	}
	public TeacherRegister() {
		super();
	}
	@Override
	public String toString() {
		return "TeacherRegister [employeeId=" + employeeId + ", name=" + name + ", dateOfBirth=" + dateOfBirth
				+ ", password=" + password + ", contactNumber=" + contactNumber + ", email=" + email + ", gender="
				+ gender + ", className=" + className + ", section=" + section + ", subject=" + subject + "]";
	}
	
	
	 
}
