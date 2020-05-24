package com.webapplication.school.app.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "attendance")
public class Attendance {

	@Id
	@Column(name = "external_id")
	private String externalId;
	@Column(name = "today_date")
	private Calendar todayDate;
	@Column(name = "roll_number")
	private String rollNumber;
	@Column(name = "name")
	private String name;
	@Column(name = "class")
	private String className;
	@Column(name = "section")
	private String section;
	@Column(name = "is_present")
	private boolean isPresent;

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public Calendar getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Calendar todayDate) {
		this.todayDate = todayDate;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Attendance(String externalId, Calendar todayDate, String rollNumber, String name, String className,
			String section, boolean isPresent) {
		super();
		this.externalId = externalId;
		this.todayDate = todayDate;
		this.rollNumber = rollNumber;
		this.name = name;
		this.className = className;
		this.section = section;
		this.isPresent = isPresent;
	}

	@Override
	public String toString() {
		return "Attendance [externalId=" + externalId + ", todayDate=" + todayDate + ", rollNumber=" + rollNumber
				+ ", name=" + name + ", className=" + className + ", section=" + section + ", isPresent=" + isPresent
				+ "]";
	}

}
