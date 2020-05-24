package com.webapplication.school.app.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.Register;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.Student;
import com.webapplication.school.app.domain.StudentRegister;
import com.webapplication.school.app.domain.Teacher;
import com.webapplication.school.app.domain.UpdatePassword;
import com.webapplication.school.app.repository.RegisterRepository;
import com.webapplication.school.app.repository.StudentRepository;
import com.webapplication.school.app.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RegisterRepository registerRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Override
	public ResponseObject login(LoginRequest stdLogin) {

		Student studentDetails = new Student();
		studentDetails = studentRepository.studentLogin(stdLogin.getContactNumber(), stdLogin.getPassword());

		if (stdLogin != null) {
			if (stdLogin.getContactNumber().equals(studentDetails.getContactNumber())) {

				LOGGER.info("Student login successful");
				return new ResponseObject(studentDetails.getContactNumber(), "Login successful", HttpStatus.OK);
			}
			return new ResponseObject(null, "Your contact number is not registered with us!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseObject register(StudentRegister stdRegister) {

		List<Student> students = studentRepository.findAll();
		if (!students.isEmpty()) {
			if (stdRegister != null) {

				for (Student student : students) {
					if (!stdRegister.getRollNumber().equals(student.getRollNumber())) {
						Student newStudent = new Student(UUID.randomUUID().toString(), stdRegister.getRollNumber(),
								Calendar.getInstance(), Calendar.getInstance(), stdRegister.getName(),
								stdRegister.getDateOfBirth(), stdRegister.getPassword(), "+91",
								stdRegister.getContactNumber(), stdRegister.getEmail(), stdRegister.getGender(),
								stdRegister.getClassName(), stdRegister.getSection(), false, false,
								Calendar.getInstance());

						studentRepository.saveAndFlush(newStudent);
						LOGGER.info("" + newStudent);

						Register register = new Register(UUID.randomUUID().toString(), Calendar.getInstance(),
								stdRegister.getName(), stdRegister.getDateOfBirth(), stdRegister.getGender(), "+91",
								stdRegister.getContactNumber(), stdRegister.getEmail());
						registerRepository.saveAndFlush(register);

						LOGGER.info("new registration - " + register);

						return new ResponseObject(null, "student created", HttpStatus.OK);
					}
					return new ResponseObject(null, "You have already registered, please login",
							HttpStatus.BAD_REQUEST);
				}
			}
		} else {
			Student newStudent = new Student(UUID.randomUUID().toString(), stdRegister.getRollNumber(),
					Calendar.getInstance(), Calendar.getInstance(), stdRegister.getName(), stdRegister.getDateOfBirth(),
					stdRegister.getPassword(), "+91", stdRegister.getContactNumber(), stdRegister.getEmail(),
					stdRegister.getGender(), stdRegister.getClassName(), stdRegister.getSection(), false, false,
					Calendar.getInstance());

			studentRepository.saveAndFlush(newStudent);
			LOGGER.info("" + newStudent);

			Register register = new Register(UUID.randomUUID().toString(), Calendar.getInstance(),
					stdRegister.getName(), stdRegister.getDateOfBirth(), stdRegister.getGender(), "+91",
					stdRegister.getContactNumber(), stdRegister.getEmail());
			registerRepository.saveAndFlush(register);

			LOGGER.info("new registration - " + register);

			return new ResponseObject(null, "student created", HttpStatus.OK);
		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseObject updatePwd(UpdatePassword updatePassword) {

		if (updatePassword != null) {
			Student student = studentRepository.findByUserId(updatePassword.getUserId());
			if (student != null) {
				if (updatePassword.getNewPassword().equals(updatePassword.getConfirmNewPassword())) {
					studentRepository.updatePwd(updatePassword.getNewPassword(), updatePassword.getUserId());

					return new ResponseObject("password updated successfully", null, HttpStatus.OK);

				}
				return new ResponseObject(null, "New password and Confirm new password does not match",
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseObject(null, "You are not registered yet!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseObject(null, "Something went wrong! please try again", HttpStatus.BAD_REQUEST);
	}

}
