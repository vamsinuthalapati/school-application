package com.webapplication.school.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.UpdatePassword;
import com.webapplication.school.app.service.UsersService;

@RestController
@RequestMapping("/app/v1")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@PostMapping("/userLogin")
	public ResponseObject studentLogin(@RequestBody LoginRequest studentLogin) throws Exception {
		return usersService.login(studentLogin);
	}
	
	@PostMapping("/updPwd")
	public ResponseObject updatePassword(@RequestBody UpdatePassword updatePassword) {
		return usersService.updatePassword(updatePassword);
	}
	
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured/hello")
	public String sayHello() {
		return "Secured hello";
	}
	
	@PostMapping("/all")
	public String handShake() {
		return "hand shake all";
	}
	
	@RequestMapping("user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
}
