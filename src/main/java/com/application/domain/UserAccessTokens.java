package com.application.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_access_tokens")
public class UserAccessTokens {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "refresh_token")
	private String refreshToken;
	@Column(name = "scopes")
	private String scopes;
	@Column(name = "created_on")
	private Calendar createdOn;
	@Column(name = "modified_on")
	private Calendar modifiedOn;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users users;

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

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public UserAccessTokens(String externalId, String refreshToken, String scopes, Calendar createdOn,
			Calendar modifiedOn, Users users) {
		super();
		this.externalId = externalId;
		this.refreshToken = refreshToken;
		this.scopes = scopes;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.users = users;
	}

	public UserAccessTokens() {
		super();
	}

	@Override
	public String toString() {
		return "UserAccessTokens [id=" + id + ", externalId=" + externalId + ", refreshToken=" + refreshToken
				+ ", scopes=" + scopes + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", users=" + users
				+ "]";
	}

}
