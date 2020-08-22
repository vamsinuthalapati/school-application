package com.application.domain;

public class UpdatePassword {

	private String externalId;
	private String newPassword;
	private String confirmNewPassword;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public UpdatePassword(String externalId, String newPassword, String confirmNewPassword) {
		super();
		this.externalId = externalId;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public UpdatePassword() {
		super();
	}

	@Override
	public String toString() {
		return "UpdatePassword [externalId=" + externalId + ", newPassword=" + newPassword + ", confirmNewPassword="
				+ confirmNewPassword + "]";
	}
}
