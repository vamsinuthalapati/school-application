package com.application.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "type")
	private String type;
	@Column(name = "created_on")
	private Calendar createdOn;
	@Column(name = "modified_on")
	private Calendar modifiedOn;
	@Column(name = "is_email_verified")
	private Boolean isEmailVerified;
	@Column(name = "stream")
	private String stream;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Boolean getIsEmailVerified() {
		return isEmailVerified;
	}

	public void setIsEmailVerified(Boolean isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Users(Long id, String externalId, String firstName, String lastName, String email, String password,
			String type, Calendar createdOn, Calendar modifiedOn, Boolean isEmailVerified) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isEmailVerified = isEmailVerified;
	}

	public Users() {
		super();
	}

	public Users(String externalId, String firstName, String lastName, String email, String password, String type,
			Calendar createdOn, Calendar modifiedOn, Boolean isEmailVerified, String stream) {
		super();
		this.externalId = externalId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.type = type;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.isEmailVerified = isEmailVerified;
		this.stream = stream;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", externalId=" + externalId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", type=" + type + ", createdOn=" + createdOn
				+ ", modifiedOn=" + modifiedOn + ", isEmailVerified=" + isEmailVerified + ", stream=" + stream + "]";
	}

}
