package com.webapplication.school.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapplication.school.app.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query("select s from Student s "
			+ "where contactNumber = :contactNumber and password = :password and s.isContactVerified = true")
	public Student studentLogin(String contactNumber, String password);

	@Query("select s from Student s")
	public List<Student> findAll();
}
