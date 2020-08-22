package com.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.domain.LoginRequest;
import com.application.domain.ResponseObject;
import com.application.service.IUserDetailsService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserDetailsService userDetailsService;

	@PostMapping("/login")
	public ResponseObject userLogin(@RequestBody LoginRequest loginRequest) {

		LOGGER.info("UserController :  userLogin initiated successfully");
		return userDetailsService.userLogin(loginRequest);
	}
}
