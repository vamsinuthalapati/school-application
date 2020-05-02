package com.webapplication.school.app.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "roll_number")
	private String rollNumber;
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
	@Column(name = "gender")
	private String gender;
	@Column(name = "reason")
	private String reason;
	@Column(name = "registration_status")
	private String registrationStatus;
	@Column(name = "is_contact_verified")
	private Boolean isContactVerified;
	@Column(name = "last_time_signed_in")
	private Calendar lastTimeSignedIn;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private Classes classes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public Boolean getIsContactVerified() {
		return isContactVerified;
	}

	public void setIsContactVerified(Boolean isContactVerified) {
		this.isContactVerified = isContactVerified;
	}

	public Calendar getLastTimeSignedIn() {
		return lastTimeSignedIn;
	}

	public void setLastTimeSignedIn(Calendar lastTimeSignedIn) {
		this.lastTimeSignedIn = lastTimeSignedIn;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Student(Long id, String externalId, String rollNumber, Calendar createdOn, Calendar modifiedOn, String name,
			Calendar dateOfBirth, String password, String countryCode, String contactNumber, String gender,
			String reason, String registrationStatus, Boolean isContactVerified, Calendar lastTimeSignedIn,
			Classes classes) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.rollNumber = rollNumber;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.countryCode = countryCode;
		this.contactNumber = contactNumber;
		this.gender = gender;
		this.reason = reason;
		this.registrationStatus = registrationStatus;
		this.isContactVerified = isContactVerified;
		this.lastTimeSignedIn = lastTimeSignedIn;
		this.classes = classes;
	}

	public Student() {
		super();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", externalId=" + externalId + ", rollNumber=" + rollNumber + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", name=" + name + ", dateOfBirth=" + dateOfBirth
				+ ", password=" + password + ", countryCode=" + countryCode + ", contactNumber=" + contactNumber
				+ ", gender=" + gender + ", reason=" + reason + ", registrationStatus=" + registrationStatus
				+ ", isContactVerified=" + isContactVerified + ", lastTimeSignedIn=" + lastTimeSignedIn + ", classes="
				+ classes + "]";
	}
	
	
}
