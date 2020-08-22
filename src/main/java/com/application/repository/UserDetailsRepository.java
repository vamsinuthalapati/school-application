package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.domain.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {

	@Query(value = "select u from Users u where u.email = :email")
	public Users userLoginEmail(String email);
	
	@Query(value = "select u from Users u where u.externalId = :externalId", nativeQuery = false)
	public Users getUserByExternalId(String externalId);
}
