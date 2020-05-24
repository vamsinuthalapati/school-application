package com.webapplication.school.app.domain;

public class UpdatePassword {

	private String userId;
	private String newPassword;
	private String confirmNewPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public UpdatePassword(String userId, String newPassword, String confirmNewPassword) {
		super();
		this.userId = userId;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	@Override
	public String toString() {
		return "UpdatePassword [userId=" + userId + ", newPassword=" + newPassword + ", confirmNewPassword="
				+ confirmNewPassword + "]";
	}

}
