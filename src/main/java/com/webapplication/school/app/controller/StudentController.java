package com.webapplication.school.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.StudentRegister;
import com.webapplication.school.app.service.StudentService;

@RestController
@RequestMapping("/app/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/stdLogin")
	public ResponseObject studentLogin(@RequestBody LoginRequest studentLogin) {
		return studentService.login(studentLogin);
	}
	
	@PostMapping("/stdRegister")
	public ResponseObject studentRegister(@RequestBody StudentRegister studentRegister) {
		return studentService.register(studentRegister);
	}
	
}
