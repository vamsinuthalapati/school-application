package com.webapplication.school.app.service;

import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.StudentRegister;

@Service
public interface StudentService {

	public ResponseObject login(LoginRequest stdLogin);
	
	public ResponseObject register(StudentRegister stdRegister);
}
