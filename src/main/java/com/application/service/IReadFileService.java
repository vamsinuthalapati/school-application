package com.application.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IReadFileService {

	public String uploadFile(MultipartFile file) throws IllegalStateException, IOException;
}
