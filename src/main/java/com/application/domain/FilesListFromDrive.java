package com.application.domain;

public class FilesListFromDrive {

	private String id;
	private String name;
	private String mimeType;
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FilesListFromDrive(String id, String name, String mimeType, String url) {
		super();
		this.id = id;
		this.name = name;
		this.mimeType = mimeType;
		this.url = url;
	}

	public FilesListFromDrive() {
		super();
	}

	@Override
	public String toString() {
		return "FilesListFromDrive [id=" + id + ", name=" + name + ", mimeType=" + mimeType + ", url=" + url + "]";
	}

}
