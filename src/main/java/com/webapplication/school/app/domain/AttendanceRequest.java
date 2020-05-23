package com.webapplication.school.app.domain;

public class AttendanceRequest extends Attendance{

	private String rollNumber;
	private String name;
	private String className;
	private String seciton;
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

	public String getSeciton() {
		return seciton;
	}

	public void setSeciton(String seciton) {
		this.seciton = seciton;
	}

	public boolean getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public AttendanceRequest(String rollNumber, String name, String className, String seciton, boolean isPresent) {
		super();
		this.rollNumber = rollNumber;
		this.name = name;
		this.className = className;
		this.seciton = seciton;
		this.isPresent = isPresent;
	}

	public AttendanceRequest() {
		super();
	}

	@Override
	public String toString() {
		return "AttendanceRequest [rollNumber=" + rollNumber + ", name=" + name + ", className=" + className
				+ ", seciton=" + seciton + ", isPresent=" + isPresent + "]";
	}

}
