package com.webapplication.school.app.service;

import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;

@Service
public interface TeacherService {

	public ResponseObject teacherLogin(LoginRequest teachLogin);
}
