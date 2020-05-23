package com.webapplication.school.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapplication.school.app.domain.Student;
import com.webapplication.school.app.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	@Query("select t from Teacher t where t.contactNumber = :contactNumber and t.password = :password")
	public Teacher teacherLogin(String contactNumber, String password);
	
	@Query("select t from Teacher t where t.isContactVerified = true")
	public List<Teacher> findAll();
	
	}
