package com.application.service;

import org.springframework.web.multipart.MultipartFile;

import com.application.domain.ChangePassword;
import com.application.domain.HeadRequestBody;
import com.application.domain.LoginRequest;
import com.application.domain.ResponseObject;
import com.application.domain.UpdatePassword;

public interface IUserDetailsService {

	public ResponseObject userLogin(LoginRequest loginRequest);

	public ResponseObject update(UpdatePassword updatePassword);

	public ResponseObject changePwd(ChangePassword changePassword, String authToken);

	public ResponseObject registerHead(HeadRequestBody headRequestBody);

	public ResponseObject registerUserExcel();
}
