package com.webapplication.school.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.Teacher;
import com.webapplication.school.app.repository.TeacherRepository;
import com.webapplication.school.app.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	public ResponseObject teacherLogin(LoginRequest teachLogin) {
		
		Teacher teacher = new Teacher();
		
		if(teachLogin != null) {
			if(teachLogin.getContactNumber().equals(teacher.getContactNumber())) {
				Teacher teacherDetails = new Teacher();
				teacherDetails = teacherRepository.teacherLogin(teachLogin.getContactNumber(), teachLogin.getPassword());
				return new ResponseObject(teacherDetails.getContactNumber(), "Login successful", HttpStatus.OK);
			}
			return new ResponseObject(null, "Your contact number is not registered with us!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

}
