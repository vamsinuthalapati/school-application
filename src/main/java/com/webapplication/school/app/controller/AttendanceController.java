package com.webapplication.school.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.school.app.domain.AttendanceRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.service.AttendanceService;

@RestController
@RequestMapping("/app/v1")
public class AttendanceController {

	private AttendanceService attendanceService;
	
//	@PostMapping("/attendance")
//	public ResponseObject attendance(@RequestBody AttendanceRequest attendanceRequest) {
//		return attendanceService.attendance(attendanceRequest);
//	}
}
