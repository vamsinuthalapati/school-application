package com.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subjects {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "stream")
	private String stream;
	@Column(name = "subjectName")
	private String subjectName;
	@Column(name = "subject_id")
	private String subjectId;
	@Column(name = "number_of_hours")
	private String numberOfHours;

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

	public String getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(String numberOfHours) {
		this.numberOfHours = numberOfHours;
	}

	public Subjects(String externalId, String stream, String subjectName, String subjectId, String numberOfHours) {
		super();
		this.externalId = externalId;
		this.stream = stream;
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.numberOfHours = numberOfHours;
	}

	public Subjects() {
		super();
	}

	@Override
	public String toString() {
		return "Subjects [id=" + id + ", externalId=" + externalId + ", stream=" + stream + ", subjectName="
				+ subjectName + ", subjectId=" + subjectId + ", numberOfHours=" + numberOfHours + "]";
	}

}
