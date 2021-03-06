package com.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.domain.ChangePassword;
import com.application.domain.HeadRequestBody;
import com.application.domain.LoginRequest;
import com.application.domain.ResponseObject;
import com.application.domain.UpdatePassword;
import com.application.service.IUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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

	@PutMapping("/resetPwd")
	public ResponseObject updatePassword(@RequestBody UpdatePassword updatePassword) {

		LOGGER.info("UserController : UpdatePassword initiated successfully");
		return userDetailsService.update(updatePassword);
	}

	@PutMapping("/updatePwd")
	public ResponseObject changePassword(@RequestBody ChangePassword changePassword,
			@RequestHeader("Authorization") String authToken) {
		return userDetailsService.changePwd(changePassword, authToken);
	}

	@PostMapping("/registerHead")
	public ResponseObject registerHead(@RequestBody HeadRequestBody headRequestBody) {
		return userDetailsService.registerHead(headRequestBody);
	}

	@PostMapping("/registerWithExcel")
	public ResponseObject registerUserExcel(@RequestParam("file") MultipartFile file,
			@RequestHeader("Authorization") String authToken) throws JsonMappingException, JsonProcessingException {
		return userDetailsService.registerUserExcel(file, authToken);
	}

	@PostMapping("/registerSubjectsWithExcel")
	public ResponseObject registerSubjectsExcel(@RequestParam("file") MultipartFile file,
			@RequestHeader("Authorization") String authToken) {
		return userDetailsService.registerSubjectsExcel(file, authToken);
	}

	@GetMapping("/listOfStudents")
	public ResponseObject getListOfStudents() {
		return userDetailsService.getListOfStudents();
	}

	@GetMapping("/listOfSubjects")
	public ResponseObject getListOfSubjects() {
		return userDetailsService.getListOfSubjects();
	}

	@PostMapping("/registerTeacher")
	public ResponseObject registerTeacher(@RequestHeader("Authorization") String authToken,
			@RequestBody HeadRequestBody headRequestBody) {
		return userDetailsService.registerTeacher(authToken, headRequestBody);
	}

	@GetMapping("/start")
	public String start() {
		return "hello world!!";
	}

}
