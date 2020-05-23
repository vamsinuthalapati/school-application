package com.webapplication.school.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapplication.school.app.domain.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long>{

}
