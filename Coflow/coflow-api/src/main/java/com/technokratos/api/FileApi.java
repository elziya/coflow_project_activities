package com.technokratos.api;

import com.technokratos.dto.response.ExceptionMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/files")
public interface FileApi {

    @ApiOperation(value = "Get file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got file successfully", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Such file doesn't exist", response = ExceptionMessage.class)
    })
    @GetMapping("/{file-id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Resource> getFile(@PathVariable("file-id") String fileId);
}

