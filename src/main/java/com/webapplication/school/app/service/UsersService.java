package com.webapplication.school.app.service;

import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.UpdatePassword;

@Service
public interface UsersService {

	public ResponseObject login(LoginRequest studentLogin) throws Exception;

	public ResponseObject updatePassword(UpdatePassword updatePassword);
}
