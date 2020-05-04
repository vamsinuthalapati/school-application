package com.webapplication.school.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.service.TeacherService;

@RestController
@RequestMapping("/app/v1")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("/teachLogin")
	public ResponseObject teacherLogin(@RequestBody LoginRequest teachLogin) {
		return teacherService.teacherLogin(teachLogin);
	}
	
	public ResponseObject teacherRegister() {
		return null;
	}
}
