
package com.application.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.constants.ErrorMessages;
import com.application.constants.MessageConstants;
import com.application.constants.SuccessMessages;
import com.application.domain.ChangePassword;
import com.application.domain.HeadRequestBody;
import com.application.domain.LoginRequest;
import com.application.domain.ResponseObject;
import com.application.domain.UpdatePassword;
import com.application.domain.Users;
import com.application.jwt.AuthUser;
import com.application.jwt.JwtAuthenticationResponse;
import com.application.repository.UserDetailsRepository;
import com.application.roles.RolesEnum;
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

		if (CommonUtils.isNull(login.getEmail()) && CommonUtils.isNull(login.getPassword())) {
			return new ResponseObject(null, ErrorMessages.LOGIN_DETAILS, HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("UserServiceImpl :  userLogin initiated successfully");
		if ((CommonUtils.isNull(login.getEmail()))) {
			return new ResponseObject(null, ErrorMessages.REGISTERED_EMAIL, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtils.isNull(login.getPassword())) {
			return new ResponseObject(null, ErrorMessages.ENTER_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (login.getEmail().contains("@")) {
			userDetails = userDetailsRepository.userLoginEmail(login.getEmail().toLowerCase());
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

			return new ResponseObject(
					new JwtAuthenticationResponse(jwt, "Bearer", userDetails.getEmail(), userDetails.getFirstName(),
							userDetails.getLastName(), userDetails.getType(), userDetails.getExternalId()),
					null, HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());

			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}

	}

	public static String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
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

	@Override
	public ResponseObject update(UpdatePassword updatePassword) {

		if (updatePassword == null) {
			return new ResponseObject(null, ErrorMessages.EMAIL_PASSWORD_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(updatePassword.getNewPassword()) || (updatePassword.getNewPassword().length() == 0))
				&& CommonUtils.isNull(updatePassword.getExternalId())) {
			return new ResponseObject(null, ErrorMessages.PROVIDE_EMAIL_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(updatePassword.getConfirmNewPassword())
				|| (updatePassword.getConfirmNewPassword().length() == 0))
				&& CommonUtils.isNull(updatePassword.getExternalId())) {
			return new ResponseObject(null, ErrorMessages.CONFIRM_EMAIL_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(updatePassword.getNewPassword()) || (updatePassword.getNewPassword().length() == 0))
				&& CommonUtils.isNull(updatePassword.getConfirmNewPassword())) {
			return new ResponseObject(null, ErrorMessages.ENTER_NEW_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(updatePassword.getExternalId())) || (updatePassword.getExternalId().length() == 0)) {
			return new ResponseObject(null, ErrorMessages.EMAIL_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(updatePassword.getNewPassword())) || (updatePassword.getNewPassword().length() == 0)) {
			return new ResponseObject(null, ErrorMessages.ENTER_NEW_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(updatePassword.getConfirmNewPassword()))
				|| (updatePassword.getConfirmNewPassword().length() == 0)) {
			return new ResponseObject(null, ErrorMessages.CONFIRM_NEW_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (!updatePassword.getNewPassword().equals(updatePassword.getConfirmNewPassword())) {
			return new ResponseObject(null, ErrorMessages.PWDS_NOT_MATCHED, HttpStatus.BAD_REQUEST);
		}
		int value = userDetailsRepository.updatePassword(updatePassword.getExternalId(),
				passwordEncoder.encode(updatePassword.getNewPassword()));
		if (value == 1) {
			return new ResponseObject(SuccessMessages.UPDATE_PASSWORD_SUCCESS, null, HttpStatus.OK);
		} else {
			return new ResponseObject(null, ErrorMessages.UPDATE_PASSWORD_FAILED, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseObject changePwd(ChangePassword changePassword, String authToken) {

		if (CommonUtils.isNull(authToken)) {
			return new ResponseObject(null, ErrorMessages.PROVIDE_AUTH_TOKEN, HttpStatus.BAD_REQUEST);
		}
		if (changePassword == null) {
			return new ResponseObject(null, ErrorMessages.BOTH_PASSWORD_REQUIRED, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtils.isNull(changePassword.getOldPassword())) {
			return new ResponseObject(null, ErrorMessages.PROVIDE_OLD_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if ((CommonUtils.isNull(changePassword.getNewPassword()) || (changePassword.getNewPassword().length() == 0))) {
			return new ResponseObject(null, ErrorMessages.PROVIDE_NEW_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (CommonUtils.isNull(changePassword.getConfirmNewPassword())) {
			return new ResponseObject(null, ErrorMessages.CONFIRM_NEW_PASSWORD, HttpStatus.BAD_REQUEST);
		}
		if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
			return new ResponseObject(null, ErrorMessages.PASSWORD_NOT_MATCHED, HttpStatus.BAD_REQUEST);
		}

		try {
			String authToken2 = authToken.substring(7);
			String externalId = getUserExternalId(authToken2);
			Users user = userDetailsRepository.getUserByExternalId(externalId);
			if (user == null) {
				return new ResponseObject(null, ErrorMessages.USER_NOT_REGISTERED, HttpStatus.BAD_REQUEST);
			}

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (!encoder.matches(changePassword.getOldPassword(), user.getPassword())) {
				return new ResponseObject(null, ErrorMessages.CORRECT_PASSWORD, HttpStatus.BAD_REQUEST);
			}
			int changedPwd = userDetailsRepository.changePwd(passwordEncoder.encode(changePassword.getNewPassword()),
					user.getExternalId());
			if (changedPwd == 1) {
				return new ResponseObject(SuccessMessages.UPDATE_PASSWORD_SUCCESS, null, HttpStatus.OK);
			}
			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			LOGGER.info(e.getMessage() + " at " + Calendar.getInstance().getTime());
			return new ResponseObject(null, ErrorMessages.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseObject registerHead(HeadRequestBody headRequestBody) {

		try {
			if (CommonUtils.isNull(headRequestBody.getEmail())) {
				return new ResponseObject(null, ErrorMessages.EMAIL_REQUIRED, HttpStatus.BAD_REQUEST);
			}
			if (CommonUtils.isNull(headRequestBody.getPassword())) {
				return new ResponseObject(null, ErrorMessages.ENTER_PASSWORD, HttpStatus.BAD_REQUEST);
			}
			if (CommonUtils.isNull(headRequestBody.getFirstName())) {
				return new ResponseObject(null, ErrorMessages.FIRST_NAME_REQUIRED, HttpStatus.BAD_REQUEST);
			}

			List<Users> users = userDetailsRepository.getAllUsers();
			for (Users allUsers : users) {
				if (allUsers.getEmail().equalsIgnoreCase(headRequestBody.getEmail())) {
					return new ResponseObject(null, ErrorMessages.EMAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
				}
			}

			Users user = new Users(UUID.randomUUID().toString(), headRequestBody.getFirstName(),
					headRequestBody.getLastName(), headRequestBody.getEmail(),
					passwordEncoder.encode(headRequestBody.getPassword()), RolesEnum.ADMIN.toString(),
					Calendar.getInstance(), Calendar.getInstance(), false);
			Users savedUser = userDetailsRepository.saveAndFlush(user);

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(savedUser.getExternalId(), headRequestBody.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.generateToken(authentication, savedUser);

			return new ResponseObject(
					new JwtAuthenticationResponse(jwt, "Bearer", savedUser.getEmail(), savedUser.getFirstName(),
							savedUser.getLastName(), savedUser.getType(), savedUser.getExternalId()),
					SuccessMessages.ACCOUNT_CREATED_SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseObject(null, e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
