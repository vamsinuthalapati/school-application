package com.application.domain;

public class SubjectsObject {

	private String stream;
	private String subjectName;
	private String subjectId;

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public SubjectsObject(String stream, String subjectName, String subjectId) {
		super();
		this.stream = stream;
		this.subjectName = subjectName;
		this.subjectId = subjectId;
	}

	public SubjectsObject() {
		super();
	}

	@Override
	public String toString() {
		return "SubjectsObject [stream=" + stream + ", subjectName=" + subjectName + ", subjectId=" + subjectId + "]";
	}

}
