package com.webapplication.school.app.service;

import org.springframework.stereotype.Service;

import com.webapplication.school.app.domain.AttendanceRequest;
import com.webapplication.school.app.domain.ResponseObject;

@Service
public interface AttendanceService {

	public ResponseObject attendance(AttendanceRequest attendanceRequest);

}
