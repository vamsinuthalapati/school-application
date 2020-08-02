package com.webapplication.school.app.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.Util.JwtUtil;
import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.UpdatePassword;
import com.webapplication.school.app.domain.Users;
import com.webapplication.school.app.repository.UserRepository;
import com.webapplication.school.app.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

	
	@Override
	public ResponseObject login(LoginRequest userLogin) throws Exception {
		
		Users users = new Users();
		users = userRepository.login(userLogin.getUserId(), userLogin.getPassword());
		
		if(users != null) {
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userLogin.getUserId(), userLogin.getPassword()));
			} catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password", e);
			}

			final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userLogin.getUserId());

			final String jwt = jwtUtil.generateToken(userDetails);


			LOGGER.info("Student login successful");
			return new ResponseObject(jwt, "Login successful", HttpStatus.OK);
		}
		return new ResponseObject(null, "Something went wrong! please try again", HttpStatus.BAD_REQUEST);
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN', 'REGULAR')")
	@Override
	public ResponseObject updatePassword(UpdatePassword updatePassword) {
		
		Users users = userRepository.findUserById(updatePassword.getUserId());
		
		if(users != null) {
			if (updatePassword.getNewPassword().equals(updatePassword.getConfirmNewPassword())) {
				userRepository.updatePwd(updatePassword.getNewPassword(), updatePassword.getUserId());
				
				return new ResponseObject("password updated successfully", null, HttpStatus.OK);
			}
		}
		return new ResponseObject(null, "Something went wrong! please check your credentials", HttpStatus.BAD_REQUEST);
	}

public void newBranch(){


