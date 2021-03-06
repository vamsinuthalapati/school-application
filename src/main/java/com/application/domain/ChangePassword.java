package com.application.domain;

public class ChangePassword {

	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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

	public ChangePassword(String oldPassword, String newPassword, String confirmNewPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public ChangePassword() {
		super();
	}

	@Override
	public String toString() {
		return "ChangePassword [oldPassword=" + oldPassword + ", newPassword=" + newPassword + ", confirmNewPassword="
				+ confirmNewPassword + "]";
	}
}
