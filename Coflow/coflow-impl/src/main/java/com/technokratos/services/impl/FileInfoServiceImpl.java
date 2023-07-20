package com.technokratos.services.impl;

import com.technokratos.models.FileInfoEntity;
import com.technokratos.repositories.FileInfoRepository;
import com.technokratos.services.FileInfoService;
import com.technokratos.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileInfoServiceImpl implements FileInfoService {

    private final FileService fileService;

    private final FileInfoRepository fileInfoRepository;

    @Override
    public void uploadFiles(MultipartFile[] files) {
        Arrays.stream(files).forEach(f -> {
            saveFileInfo(f, fileService.uploadFile(f));
        });
    }

    @Override
    public FileInfoEntity uploadFile(MultipartFile file) {
        return saveFileInfo(file, fileService.uploadFile(file));
    }

    private FileInfoEntity saveFileInfo(MultipartFile multipart, String storageFileName) {
        FileInfoEntity file = FileInfoEntity.builder()
                .type(multipart.getContentType())
                .origName(multipart.getOriginalFilename())
                .description("")
                .storageName(storageFileName)
                .size(multipart.getSize())
                .build();

        fileInfoRepository.save(file);

        return file;
    }
}

