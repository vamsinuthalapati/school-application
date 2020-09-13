package com.application.domain;

import java.util.Calendar;

public class StudentFilesObject {

	private String fileId;
	private String fileUrl;
	private String mimeType;
	private String sharedBy;
	private Calendar createdOn;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getSharedBy() {
		return sharedBy;
	}

	public void setSharedBy(String sharedBy) {
		this.sharedBy = sharedBy;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public StudentFilesObject(String fileId, String fileUrl, String mimeType, String sharedBy, Calendar createdOn) {
		super();
		this.fileId = fileId;
		this.fileUrl = fileUrl;
		this.mimeType = mimeType;
		this.sharedBy = sharedBy;
		this.createdOn = createdOn;
	}

	public StudentFilesObject() {
		super();
	}

	@Override
	public String toString() {
		return "StudentFilesObject [fileId=" + fileId + ", fileUrl=" + fileUrl + ", mimeType=" + mimeType
				+ ", sharedBy=" + sharedBy + ", createdOn=" + createdOn + "]";
	}

}
