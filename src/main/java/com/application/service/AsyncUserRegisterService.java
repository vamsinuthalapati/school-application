package com.application.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.domain.RegisterUserWithExcel;
import com.application.domain.Users;
import com.application.repository.UserDetailsRepository;
import com.application.roles.RolesEnum;
import com.application.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AsyncUserRegisterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncUserRegisterService.class);

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider tokenProvider;

	@Autowired
	public AsyncUserRegisterService(AuthenticationManager authenticationManager, UserDetailsRepository userRepository,
			PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userDetailsRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	public static final String PASSWORD = "Test@123";

	@Async
	public void registerUsersAsync(List<RegisterUserWithExcel> list) {

		List<RegisterUserWithExcel> excelList = list;

		List<RegisterUserWithExcel> dbList = userDetailsRepository.getAllUsersExcelClass();

		List<RegisterUserWithExcel> removeList = new ArrayList<>();

		LOGGER.info("excel List :" + excelList);

		LOGGER.info("db List :" + dbList);

		for (int i = 0; i < excelList.size(); i++) {

			for (int j = 0; j < dbList.size(); j++) {

				if (excelList.get(i).getEmail().equals(dbList.get(j).getEmail())) {

					removeList.add(dbList.get(j));
				}
			}
		}

		LOGGER.info("remove List :" + removeList);
		if (removeList.size() != 0) {
			LOGGER.info("removing already registered account List :" + excelList.removeAll(removeList));
		}
		LOGGER.info("final List :" + excelList);

		for (int k = 0; k < excelList.size(); k++) {

			Users user = new Users(UUID.randomUUID().toString(), excelList.get(k).getFirstName(),
					excelList.get(k).getLastName(), excelList.get(k).getEmail(), passwordEncoder.encode(PASSWORD),
					excelList.get(k).getType(), Calendar.getInstance(), Calendar.getInstance(), false);
			Users savedUser = userDetailsRepository.saveAndFlush(user);

			LOGGER.info("registered user :" + excelList.get(k).getEmail());
		}
	}
}
