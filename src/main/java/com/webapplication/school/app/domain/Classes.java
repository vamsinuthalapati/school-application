package com.webapplication.school.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "class")
public class Classes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "class_name")
	private String className;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Classes(Long id, String className) {
		super();
		this.id = id;
		this.className = className;
	}
	
	public Classes() {
		super();
	}
	
	@Override
	public String toString() {
		return "Classes [id=" + id + ", className=" + className + "]";
	}
	
	
}
