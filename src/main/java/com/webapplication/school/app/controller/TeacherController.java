package com.webapplication.school.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.school.app.domain.AttendanceRequest;
import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.TeacherRegister;
import com.webapplication.school.app.domain.UpdatePassword;
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
	
	@PostMapping("/teachRegister")
	public ResponseObject teacherRegister(@RequestBody TeacherRegister teacherRegister) {
		return teacherService.teacherRegister(teacherRegister);
	}
	
	@PostMapping("/takeAttend")
	public ResponseObject takeAttendance(@RequestBody AttendanceRequest attendanceRequest) {
		return teacherService.studentAttendance(attendanceRequest);
	}
	
	@PutMapping("/updPwd")
	public ResponseObject updateTeacherPassword(@RequestBody UpdatePassword updatePassword) {
		return teacherService.updatePwd(updatePassword);
	}
}
