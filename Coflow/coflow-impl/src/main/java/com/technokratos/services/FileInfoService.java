package com.technokratos.services;

import com.technokratos.models.FileInfoEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileInfoService {

    void uploadFiles(MultipartFile[] files);

    FileInfoEntity uploadFile(MultipartFile file);
}

