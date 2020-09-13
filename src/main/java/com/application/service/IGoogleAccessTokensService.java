package com.application.service;

import org.springframework.stereotype.Service;

import com.application.domain.ResponseObject;

@Service
public interface IGoogleAccessTokensService {

	public ResponseObject storeTokens(String authToken, String code);

	public ResponseObject getUser(String authToken);

}
