package com.webapplication.school.app.service;

import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.AttendanceRequest;
import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.TeacherRegister;
import com.webapplication.school.app.domain.UpdatePassword;

@Service
public interface TeacherService {

	public ResponseObject teacherLogin(LoginRequest teachLogin);
	
	public ResponseObject teacherRegister(TeacherRegister teacherRegister);
	
	public ResponseObject studentAttendance(AttendanceRequest attendanceRequest);
	
	public ResponseObject updatePwd(UpdatePassword updatePassword);
	
}
