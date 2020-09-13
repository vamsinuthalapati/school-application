package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.domain.ResponseObject;
import com.application.service.IDriveService;

import net.minidev.json.parser.ParseException;

@RestController
@RequestMapping("/api/v1/drive")
public class DriveController {

	@Autowired
	public IDriveService iDriveService;

	@GetMapping("/filesList")
	public ResponseObject getFilesList(@RequestHeader("code") String code, @RequestHeader("fileType") String fileType,
			@RequestHeader("Authorization") String authToken) {
		return iDriveService.getDriveFilesList(code, fileType, authToken);
	}

	@PostMapping("/shareFiles")
	public ResponseObject shareFiles(@RequestHeader("code") String code, @RequestHeader String fileId,
			@RequestHeader("Authorization") String authToken) {
		return iDriveService.shareFileWithPermissions(code, fileId, authToken);
	}

	@GetMapping("/sharedFilesList")
	public ResponseObject listOfFiles(@RequestHeader("Authorization") String authToken) {
		return iDriveService.getListOFStudentFiles(authToken);
	}
}
