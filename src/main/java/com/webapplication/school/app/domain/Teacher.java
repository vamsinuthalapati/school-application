package com.webapplication.school.app.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "employee_id")
	private String employeeId;
	@Column(name = "created_on")
	private Calendar createdOn;
	@Column(name = "modified_on")
	private Calendar modifiedOn;
	@Column(name = "name")
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
	@Column(name = "date_of_birth")
	private Calendar dateOfBirth;
	@Column(name = "password")
	private String password;
	@Column(name = "country_code")
	private String countryCode;
	@Column(name = "contact_number")
	private String contactNumber;
	@Column(name = "email")
	private String email;
	@Column(name = "gender")
	private String gender;
	@Column(name = "class")
	private String className;
	@Column(name = "section")
	private String section;
	@Column(name = "subject")
	private String subject;
	@Column(name = "is_contact_verified")
	private Boolean isContactVerified;
	@Column(name = "is_email_verified")
	private Boolean isEmailVerified;
	@Column(name = "last_time_signed_in")
	private Calendar lastTimeSignedIn;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public Calendar getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public Boolean getIsContactVerified() {
		return isContactVerified;
	}

	public void setIsContactVerified(Boolean isContactVerified) {
		this.isContactVerified = isContactVerified;
	}

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public Calendar getLastTimeSignedIn() {
		return lastTimeSignedIn;
	}

	public void setLastTimeSignedIn(Calendar lastTimeSignedIn) {
		this.lastTimeSignedIn = lastTimeSignedIn;
	}

	public Teacher(String externalId, String employeeId, Calendar createdOn, Calendar modifiedOn, String name,
			Calendar dateOfBirth, String password, String countryCode, String contactNumber, String email,
			String gender, String className, String section, String subject, Boolean isContactVerified,
			Boolean isEmailVerified, Calendar lastTimeSignedIn) {
		super();
		this.externalId = externalId;
		this.employeeId = employeeId;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.countryCode = countryCode;
		this.contactNumber = contactNumber;
		this.email = email;
		this.gender = gender;
		this.className = className;
		this.section = section;
		this.subject = subject;
		this.isContactVerified = isContactVerified;
		this.isEmailVerified = isEmailVerified;
		this.lastTimeSignedIn = lastTimeSignedIn;
	}

	public Teacher() {
		super();
	}

	@Override
	public String toString() {
		return "Teacher [externalId=" + externalId + ", employeeId=" + employeeId + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", password="
				+ password + ", countryCode=" + countryCode + ", contactNumber=" + contactNumber + ", email=" + email
				+ ", gender=" + gender + ", className=" + className + ", section=" + section + ", subject=" + subject
				+ ", isContactVerified=" + isContactVerified + ", isEmailVerified=" + isEmailVerified
				+ ", lastTimeSignedIn=" + lastTimeSignedIn + "]";
	}

}
