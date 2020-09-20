package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.domain.Subjects;
import com.application.domain.SubjectsObject;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

	@Query(value = "select new com.application.domain.SubjectsObject(s.stream, s.subjectName, s.subjectId) from Subjects s", nativeQuery = false)
	public List<SubjectsObject> getAllSubjectsExcelClass();

}
