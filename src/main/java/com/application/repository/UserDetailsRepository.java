package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.application.domain.RegisterUserWithExcel;
import com.application.domain.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Long> {

	@Query(value = "select u from Users u where u.email = :email", nativeQuery = false)
	public Users userLoginEmail(String email);

	@Query(value = "select u from Users u where u.externalId = :externalId", nativeQuery = false)
	public Users getUserByExternalId(String externalId);

	@Transactional
	@Modifying
	@Query(value = "update Users u set u.password = :password where u.externalId = :externalId", nativeQuery = false)
	public int updatePassword(String externalId, String password);

	@Transactional
	@Modifying
	@Query(value = "update Users u set u.password = :newPassword where u.externalId = :externalId", nativeQuery = false)
	public int changePwd(String newPassword, String externalId);

	@Query(value = "select u from Users u")
	public List<Users> getAllUsers();

	@Query(value = "select new com.application.domain.RegisterUserWithExcel(u.email, u.firstName, u.lastName, u.type) from Users u", nativeQuery = false)
	public List<RegisterUserWithExcel> getAllUsersExcelClass();

	@Query(value = "select u from Users u where u.type = :type", nativeQuery = false)
	public List<Users> getAllUsersByType(String type);
}
