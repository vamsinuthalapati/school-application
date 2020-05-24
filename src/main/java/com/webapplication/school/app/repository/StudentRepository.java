package com.webapplication.school.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webapplication.school.app.domain.Student;
import com.webapplication.school.app.domain.Teacher;

@Repository
public interface StudentRepository extends JpaRepository<Student, String>{

	@Query(value = "select s from Student s "
			+ "where s.contactNumber = :contactNumber and s.password = :password and s.isContactVerified = true", nativeQuery = false)
	public Student studentLogin(String contactNumber, String password);

	@Query(value = "select s from Student s", nativeQuery = false)
	public List<Student> findAll();
	
	@Query(value = "select s from Student s where s.className = :className or s.section = :section", nativeQuery = false)
	public List<Student> takeAttend(@Param("className") String className, @Param ("section") String section);
	
	@Query(value = "select s from Student s where s.rollNumber = :userId", nativeQuery = false)
	public Student findByUserId(@Param ("userId") String userId);
	
	@Transactional
	@Modifying
	@Query(value = "update Student s set s.password = :newPassword where s.rollNumber = :userId ", nativeQuery = false)
	public int updatePwd(@Param ("newPassword") String newPassword, @Param ("userId") String userId);
}
