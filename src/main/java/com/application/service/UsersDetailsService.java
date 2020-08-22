
package com.application.service;

import java.text.ParseException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.constants.ErrorMessages;
import com.application.constants.MessageConstants;
import com.application.domain.LoginRequest;
import com.application.domain.ResponseObject;
import com.application.domain.Users;
import com.application.jwt.AuthUser;
import com.application.jwt.JwtAuthenticationResponse;
import com.application.repository.UserDetailsRepository;
import com.application.security.JwtTokenProvider;
import com.application.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;

@Service
public class UsersDetailsService implements IUserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsersDetailsService.class);

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	private AuthenticationManager authenticationManager;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider tokenProvider;

	@Autowired
	public UsersDetailsService(AuthenticationManager authenticationManager, UserDetailsRepository userRepository,
			PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userDetailsRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}

	@Override
	public ResponseObject userLogin(LoginRequest login) {
		Users userDetails = new Users();

		if (CommonUtils.isNull(login.getEmailId()) && CommonUtils.isNull(login.getPassword())) {
			return new ResponseObject(null, ErrorMessages.LOGIN_DETAILS, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("UserServiceImpl :  userLogin initiated successfully");
		if ((CommonUtils.isNull(login.getEmailId()))) {
			return new ResponseObject(null, ErrorMessages.REGISTERED_EMAIL, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtils.isNull(login.getPassword())) {
			return new ResponseObject(null, ErrorMessages.ENTER_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (login.getEmailId().contains("@")) {
			userDetails = userDetailsRepository.userLoginEmail(login.getEmailId().toLowerCase());
		}
		if (userDetails == null) {
			return new ResponseObject(null, ErrorMessages.NOT_REGISTERED, HttpStatus.BAD_REQUEST);
		}

		if ((CommonUtils.isNull(login.getPassword())) || login.getPassword().length() == 0) {
			return new ResponseObject(null, ErrorMessages.ENTER_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(userDetails.getPassword())) || (userDetails.getPassword().length() == 0)) {
			return new ResponseObject(null, ErrorMessages.PASSWORD_NOT_SET, HttpStatus.BAD_REQUEST);
		}
		if (!passwordEncoder.matches(login.getPassword(), userDetails.getPassword())) {
			return new ResponseObject(null, ErrorMessages.INCORRECT_PASSWORD, HttpStatus.BAD_REQUEST);
		}

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userDetails.getExternalId(), login.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(authentication, userDetails);

			return new ResponseObject(new JwtAuthenticationResponse(jwt, "Bearer", userDetails.getEmail(),
					userDetails.getFirstName(), userDetails.getLastName(), userDetails.getExternalId()), null,
					HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());

			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}

	}

	public static String getUserExternalId(String authToken) {

		// String token = resolveToken(authToken);
		String token = authToken;
		if (token == null) {
			return MessageConstants.UNAUTHORIZED;
		}
		SignedJWT signedJWT;
		ObjectMapper mapper = new ObjectMapper();
		try {
			signedJWT = SignedJWT.parse(token);
			String object = signedJWT.getPayload().toJSONObject().toJSONString();
			AuthUser user = mapper.readValue(object, AuthUser.class);
			if (System.currentTimeMillis() > user.getSub()) {
				return MessageConstants.UNAUTHORIZED;
			} else {
				return user.getJti();
			}
		} catch (ParseException | JsonProcessingException jpe) {
			LOGGER.info(jpe.getMessage() + " at " + Calendar.getInstance().getTime());
			LOGGER.error(jpe.getMessage(), jpe);

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			LOGGER.error(e.getMessage(), e);
		}

		return MessageConstants.UNAUTHORIZED;
	}
}
