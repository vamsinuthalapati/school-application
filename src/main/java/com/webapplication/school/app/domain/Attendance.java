package com.webapplication.school.app.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "attendace")
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	@Column(name = "date")
	private Calendar date;
	@Column(name = "std_roll_number")
	private String stdRollNumber;
	@Column(name = "class_name")
	private String className;
	@Column(name = "section")
	private String section;
	@Column(name = "present")
	private boolean present;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "std_id")
	private Student student;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getStdRollNumber() {
		return stdRollNumber;
	}

	public void setStdRollNumber(String stdRollNumber) {
		this.stdRollNumber = stdRollNumber;
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
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Attendance(String id, Calendar date, String stdRollNumber, String className, String section, boolean present,
			Student student) {
		super();
		this.id = id;
		this.date = date;
		this.stdRollNumber = stdRollNumber;
		this.className = className;
		this.section = section;
		this.present = present;
		this.student = student;
	}
	
	

	public Attendance() {
		super();
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", date=" + date + ", stdRollNumber=" + stdRollNumber + ", className="
				+ className + ", section=" + section + ", present=" + present + ", student=" + student + "]";
	}
	
	
}
