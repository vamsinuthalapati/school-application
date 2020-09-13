package com.application.repository;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.application.domain.UserAccessTokens;

@Repository
public interface GoogleAccessTokensRepository extends JpaRepository<UserAccessTokens, Long> {

	@Transactional
	@Modifying
	@Query(value = "update UserAccessTokens g set g.refreshToken = :refreshToken, g.modifiedOn = :instance where g.users.id = :id", nativeQuery = false)
	public void updateToken(String refreshToken, Calendar instance, Long id);

	@Query(value = "select g from UserAccessTokens g where g.users.id = :id ", nativeQuery = false)
	public UserAccessTokens getUserById(Long id);

}
