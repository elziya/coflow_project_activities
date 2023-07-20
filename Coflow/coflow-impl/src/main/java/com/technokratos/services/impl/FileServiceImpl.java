package com.technokratos.services.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.technokratos.exceptions.CoflowFileNotFoundException;
import com.technokratos.exceptions.CoflowFileUploadException;
import com.technokratos.repositories.FileInfoRepository;
import com.technokratos.services.FileService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {

    private final GridFsTemplate gridFsTemplate;

    private final GridFSBucket gridFSBucket;

    @Override
    public ResponseEntity<Resource> getFileResourceById(String fileId) {
        GridFSFile dbFile = Optional.ofNullable(gridFsTemplate
                .findOne(new Query(Criteria.where("_id").is(fileId))))
                .orElseThrow(CoflowFileNotFoundException::new);

        GridFsResource gridFsResource = new GridFsResource(dbFile, gridFSBucket.openDownloadStream(dbFile.getObjectId()));
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(gridFsResource.getContentType()))
                .body(gridFsResource);
    }

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            ObjectId fileId = gridFsTemplate.store(file.getInputStream(),
                                                   file.getOriginalFilename(),
                                                   file.getContentType());
            return String.valueOf(fileId);
        } catch (IOException e) {
            throw new CoflowFileUploadException();
        }
    }

    @Override
    public List<String> uploadMultipleFiles(MultipartFile[] multipartFiles) {
        return Arrays.stream(multipartFiles).map(this::uploadFile).collect(Collectors.toList());
    }
}

