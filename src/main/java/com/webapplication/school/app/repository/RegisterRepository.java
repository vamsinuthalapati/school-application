package com.webapplication.school.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.webapplication.school.app.domain.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, String>{

	@Query(value = "select r from Register r")
	public List<Register> getAllRegistered();
}
