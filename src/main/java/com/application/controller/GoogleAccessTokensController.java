package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.domain.ResponseObject;
import com.application.service.IGoogleAccessTokensService;

@RestController
@RequestMapping("/api/v1")
public class GoogleAccessTokensController {

	@Autowired
	public IGoogleAccessTokensService iGoogleAccessTokenService;

	@PostMapping("/storeTokens")
	public ResponseObject storeUserAccessTokens(@RequestHeader("Authorization") String authToken,
			@RequestHeader("code") String code) {
		return iGoogleAccessTokenService.storeTokens(authToken, code);
	}

	@GetMapping("/user/me")
	public ResponseObject getUserInfo(@RequestHeader("Authorization") String authToken) {
		return iGoogleAccessTokenService.getUser(authToken);
	}
}
