package com.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.domain.Users;
import com.application.repository.UserDetailsRepository;

import javassist.NotFoundException;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserDetailsRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String externalId) {

		Users user = userRepository.getUserByExternalId(externalId);
		if (user == null) {
			return (UserDetails) new NotFoundException("User not found [id: " + externalId + "]");
		}

		return (UserDetails) UserPrincipal.create(user);
	}

	@Transactional
	public UserDetails loadUserById(Long id) throws NotFoundException {

		Users user = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("User not found [id: " + id + "]"));

		return (UserDetails) UserPrincipal.create(user);
	}

}
