package com.webapplication.school.app.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.Attendance;
import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.Student;
import com.webapplication.school.app.domain.StudentRegister;
import com.webapplication.school.app.repository.AttendanceRepository;
import com.webapplication.school.app.repository.StudentRepository;
import com.webapplication.school.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	private AttendanceRepository attendanceRepository;

	@Override
	public ResponseObject login(LoginRequest stdLogin) {

		Student student = new Student();

		if (stdLogin != null) {
			if (stdLogin.getContactNumber().equals(student.getContactNumber())) {
				Student studentDetails = new Student();
				studentDetails = studentRepository.studentLogin(stdLogin.getContactNumber(), stdLogin.getPassword());
				return new ResponseObject(studentDetails.getContactNumber(), "Login successful", HttpStatus.OK);
			}
			return new ResponseObject(null, "Your contact number is not registered with us!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseObject register(StudentRegister stdRegister) {

		if (stdRegister != null) {

			List<Student> students = studentRepository.findAll();
			for (Student student : students) {
				if (stdRegister.getRollNumber().equals(student.getRollNumber())) {
					Student newStudent = new Student(UUID.randomUUID().toString(), stdRegister.getRollNumber(),
							Calendar.getInstance(), Calendar.getInstance(), stdRegister.getName(),
							stdRegister.getDateOfBirth(), stdRegister.getPassword(), "+91",
							stdRegister.getContactNumber(), stdRegister.getEmail(), stdRegister.getGender(),
							stdRegister.getClassName(), stdRegister.getSection(), false, false, Calendar.getInstance());
					studentRepository.saveAndFlush(newStudent);

					Attendance attendance = new Attendance(Calendar.getInstance(), stdRegister.getRollNumber(),
							stdRegister.getClassName(), stdRegister.getSection(), false, newStudent);
					attendanceRepository.saveAndFlush(attendance);
				}
				return new ResponseObject(null, "You have already registered, please login", HttpStatus.BAD_REQUEST);
			}

		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

}
