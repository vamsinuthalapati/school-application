package com.application.service;

import com.application.domain.LoginRequest;
import com.application.domain.ResponseObject;

public interface IUserDetailsService {

	public ResponseObject userLogin(LoginRequest loginRequest);
}
