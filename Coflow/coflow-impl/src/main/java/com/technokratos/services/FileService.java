package com.technokratos.services;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    ResponseEntity<Resource> getFileResourceById(String fileId);

    String uploadFile(MultipartFile file);

    List<String> uploadMultipleFiles(MultipartFile[] multipartFiles);
}

