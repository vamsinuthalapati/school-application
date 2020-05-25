package com.webapplication.school.app.domain;

public class AttendanceRequest {

	private String rollNumber;
	private String name;
	private String className;
	private String section;
	private boolean isPresent;

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

	public AttendanceRequest(String rollNumber, String name, String className, String section, boolean isPresent) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.className = className;
		this.section = section;
		this.isPresent = isPresent;
	}

	@Override
	public String toString() {
		return "AttendanceRequest [rollNumber=" + rollNumber + ", name=" + name + ", className=" + className
				+ ", section=" + section + ", isPresent=" + isPresent + "]";
	}

	
}
