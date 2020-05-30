package com.webapplication.school.app.domain;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

	@Id
	@Column(name = "role_id")
	private Long roleId;
	@Column(name = "role")
	private String role;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Roles(Long roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}

	public Roles() {
		super();
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", role=" + role + "]";
	}

}
