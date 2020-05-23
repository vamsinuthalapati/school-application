package com.webapplication.school.app.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.Attendance;
import com.webapplication.school.app.domain.AttendanceRequest;
import com.webapplication.school.app.domain.ResponseObject;
import com.webapplication.school.app.repository.AttendanceRepository;
import com.webapplication.school.app.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService{

	private AttendanceRepository attendanceRepository;
	
	@Override
	public ResponseObject attendance(AttendanceRequest attendanceRequest) {
		if(attendanceRequest != null) {
			Attendance attendance = new AttendanceRequest(attendanceRequest.getRollNumber(), attendanceRequest.getName(),
					attendanceRequest.getClassName(), attendanceRequest.getSeciton(), attendanceRequest.getIsPresent());
			
			attendanceRepository.saveAndFlush(attendance);
		}
		return new ResponseObject(null, "Something went wrong! please try refreshing the page", HttpStatus.BAD_REQUEST);
	}

}
