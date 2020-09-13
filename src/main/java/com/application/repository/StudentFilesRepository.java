package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.domain.StudentFilesDomain;
import com.application.domain.StudentFilesObject;

@Repository
public interface StudentFilesRepository extends JpaRepository<StudentFilesDomain, Long> {

	@Query(value = "select new com.application.domain.StudentFilesObject(s.fileId, s.fileUrl, s.mimeType, s.sharedBy, s.createdOn) from StudentFilesDomain s", nativeQuery = false)
	public List<StudentFilesObject> getFiles();

}
