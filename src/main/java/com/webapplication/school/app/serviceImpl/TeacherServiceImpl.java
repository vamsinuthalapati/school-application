package com.webapplication.school.app.serviceImpl;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.webapplication.school.app.domain.Attendance;
import com.webapplication.school.app.domain.AttendanceRequest;
import com.webapplication.school.app.domain.LoginRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.domain.Student;
import com.webapplication.school.app.domain.Teacher;
import com.webapplication.school.app.domain.TeacherRegister;
import com.webapplication.school.app.domain.UpdatePassword;
import com.webapplication.school.app.repository.AttendanceRepository;
import com.webapplication.school.app.repository.StudentRepository;
import com.webapplication.school.app.repository.TeacherRepository;
import com.webapplication.school.app.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private StudentRepository studentRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherServiceImpl.class);

	@Override
	public ResponseObject teacherLogin(LoginRequest teachLogin) {

		Teacher teacherDetails = new Teacher();
		teacherDetails = teacherRepository.teacherLogin(teachLogin.getContactNumber(), teachLogin.getPassword());
		if (teachLogin != null) {
			if (teachLogin.getContactNumber().equals(teacherDetails.getContactNumber())) {
				teacherDetails = teacherRepository.teacherLogin(teachLogin.getContactNumber(),
						teachLogin.getPassword());
				LOGGER.info("Login successful");
				return new ResponseObject(teacherDetails.getContactNumber(), "Login successful", HttpStatus.OK);
			}
			return new ResponseObject(null, "Your contact number is not registered with us!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseObject(null, "Something went wrong! Please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseObject teacherRegister(TeacherRegister teacherRegister) {

		List<Teacher> teachers = teacherRepository.findAll();
		if (teacherRegister != null) {
			for (Teacher teach : teachers) {
				if (!teacherRegister.getEmployeeId().equals(teach.getEmployeeId())) {
					Teacher teacher = new Teacher(UUID.randomUUID().toString(), teacherRegister.getEmployeeId(),
							Calendar.getInstance(), Calendar.getInstance(), teacherRegister.getName(),
							teacherRegister.getDateOfBirth(), teacherRegister.getPassword(), "+91",
							teacherRegister.getContactNumber(), teacherRegister.getEmail(), teacherRegister.getGender(),
							teacherRegister.getClassName(), teacherRegister.getSection(), teacherRegister.getSubject(),
							false, false, Calendar.getInstance());

					teacherRepository.saveAndFlush(teacher);
					LOGGER.info("" + teacher);

					return new ResponseObject(null, "Teacher account created", HttpStatus.OK);
				}
				return new ResponseObject(null, "You have already registered, please login", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseObject(null, "Something went wrong! please refresh the page", HttpStatus.OK);
	}

	@Override
	public ResponseObject studentAttendance(AttendanceRequest attendanceRequest) {

		List<Student> students = studentRepository.findAll();
		if (students != null) {
			for (Student student : students) {
				if (student.getRollNumber().equalsIgnoreCase(attendanceRequest.getRollNumber())) {
					Attendance attendance = new Attendance(UUID.randomUUID().toString(), Calendar.getInstance(), student.getRollNumber(),
							student.getName(), student.getClassName(), student.getSection(),
							attendanceRequest.isPresent());
					attendanceRepository.saveAndFlush(attendance);
					return new ResponseObject("Attendance taken successfully", null, HttpStatus.OK);
				}
				return new ResponseObject(null, "Roll Number not found", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseObject(null, "No students assigned to your class", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseObject updatePwd(UpdatePassword updatePassword) {

		if (updatePassword != null) {
			Teacher teacher = teacherRepository.findByUserId(updatePassword.getUserId());
			if (teacher != null) {
				if (updatePassword.getNewPassword().equals(updatePassword.getConfirmNewPassword())) {
					teacherRepository.updatePwd(updatePassword.getNewPassword(), updatePassword.getUserId());

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
