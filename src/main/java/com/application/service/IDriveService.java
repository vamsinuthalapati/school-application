package com.application.service;

import org.springframework.stereotype.Service;

import com.application.domain.ResponseObject;

import net.minidev.json.parser.ParseException;

@Service
public interface IDriveService {

	public ResponseObject getDriveFilesList(String code, String fileType);

}
