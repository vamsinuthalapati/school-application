package com.webapplication.school.app.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "register")
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "external_id")
	private String externalId;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
	@Column(name = "joining_date")
	private Calendar joiningDate;
	@Column(name = "name")
	private String name;
	@Column(name = "date_of_birth")
	private Calendar dateOfBirth;
	@Column(name = "gender")
	private String gender;
	@Column(name = "country_code")
	private String countryCode;
	@Column(name = "contact_number")
	private String contantNumber;
	@Column(name = "email")
	private String email;

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

	public Calendar getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Calendar joiningDate) {
		this.joiningDate = joiningDate;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getContantNumber() {
		return contantNumber;
	}

	public void setContantNumber(String contantNumber) {
		this.contantNumber = contantNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Register(Long id, String externalId, Calendar joiningDate, String name, Calendar dateOfBirth, String gender,
			String countryCode, String contantNumber, String email) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.joiningDate = joiningDate;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.countryCode = countryCode;
		this.contantNumber = contantNumber;
		this.email = email;
	}

	public Register(String externalId, Calendar joiningDate, String name, Calendar dateOfBirth, String gender,
			String countryCode, String contantNumber, String email) {
		super();
		this.externalId = externalId;
		this.joiningDate = joiningDate;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.countryCode = countryCode;
		this.contantNumber = contantNumber;
		this.email = email;
	}

	public Register() {
		super();
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", externalId=" + externalId + ", joiningDate=" + joiningDate + ", name=" + name
				+ ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", countryCode=" + countryCode
				+ ", contantNumber=" + contantNumber + ", email=" + email + "]";
	}

}
