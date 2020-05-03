package com.webapplication.school.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.Student;
import com.webapplication.school.app.repository.StudentRepository;
import com.webapplication.school.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public ResponseObject login(LoginRequest stdLogin) {
		
		Student student = new Student();
		
		if(stdLogin != null) {
			if(stdLogin.getContactNumber().equals(student.getContactNumber())) {
				Student studentDetails = new Student();
				studentDetails = studentRepository.studentLogin(stdLogin.getContactNumber(), stdLogin.getPassword());
				return new ResponseObject(studentDetails.getContactNumber(), "Login successful", HttpStatus.OK);
			}
			return new ResponseObject(null, "Your contact number is not registered with us!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

	
}
