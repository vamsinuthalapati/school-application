package com.webapplication.school.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webapplication.school.app.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

	@Query(value = "select t from Teacher t where t.employeeId = :userId and t.password = :password")
	public Teacher teacherLogin(String userId, String password);

	@Query(value = "select t from Teacher t ")
	public List<Teacher> findAll();

	@Query(value = "select t from Teacher t where t.employeeId = :userId")
	public Teacher findByUserId(@Param ("userId") String userId);
	
	@Transactional
	@Modifying
	@Query(value = "update Teacher t set t.password = :newPassword where t.employeeId = :userId ")
	public int updatePwd(String newPassword, String userId);
}
