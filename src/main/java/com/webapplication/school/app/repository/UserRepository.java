package com.webapplication.school.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webapplication.school.app.domain.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query(value = "select u from Users u where u.userId = :username", nativeQuery = false)
	Optional<Users> findByName(String username);

	@Query(value = "select u from Users u where u.userId = :userId and u.password = :password", nativeQuery = false)
	public Users login(String userId, String password);
	
	@Query(value = "select u from Users u where u.userId = :userId", nativeQuery = false)
	public Users findUserById(String userId);
	
	@Transactional
	@Modifying
	@Query(value = "update Users u set u.password = :newPassword where u.userId = :userId", nativeQuery = false)
	public int updatePwd(String newPassword, String userId);

}
