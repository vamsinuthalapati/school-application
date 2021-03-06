package com.application.service;

import org.springframework.stereotype.Service;

import com.application.domain.ResponseObject;

@Service
public interface IDriveService {

	public ResponseObject getDriveFilesList(String code, String fileType, String authToken);

	public ResponseObject shareFileWithPermissions(String code, String fileId, String authToken);

	public ResponseObject getListOFStudentFiles(String authToken);

	public ResponseObject getListOfFilesSharedMe(String authToken);

	public ResponseObject getChildFilesInFolder(String authToken, String folderId, String code);

}
