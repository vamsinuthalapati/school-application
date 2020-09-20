package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.domain.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

}
