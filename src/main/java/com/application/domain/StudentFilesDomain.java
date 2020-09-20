package com.application.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_files")
public class StudentFilesDomain {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "file_id")
	private String fileId;
	@Column(name = "file_url")
	private String fileUrl;
	@Column(name = "mime_type")
	private String mimeType;
	@Column(name = "shared_by")
	private String sharedBy;
	@Column(name = "department")
	private String stream;
	@Column(name = "created_on")
	private Calendar createdOn;
	@Column(name = "modified_on")
	private Calendar modifiedOn;

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

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
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

	public StudentFilesDomain(String externalId, String fileId, String fileUrl, String mimeType, String sharedBy,
			String stream, Calendar createdOn, Calendar modifiedOn) {
		super();
		this.externalId = externalId;
		this.fileId = fileId;
		this.fileUrl = fileUrl;
		this.mimeType = mimeType;
		this.sharedBy = sharedBy;
		this.stream = stream;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	public StudentFilesDomain() {
		super();
	}

	@Override
	public String toString() {
		return "StudentFilesDomain [id=" + id + ", externalId=" + externalId + ", fileId=" + fileId + ", fileUrl="
				+ fileUrl + ", mimeType=" + mimeType + ", sharedBy=" + sharedBy + ", stream=" + stream + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + "]";
	}

}
