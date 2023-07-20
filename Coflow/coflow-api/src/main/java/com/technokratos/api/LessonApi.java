package com.technokratos.api;

import com.technokratos.dto.request.LessonCreationForm;
import com.technokratos.dto.response.ExceptionMessage;
import com.technokratos.dto.response.LessonResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1")
public interface LessonApi<PRINCIPAL> {

    @ApiOperation(value = "Create lesson")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add lesson successfully", response = UUID.class),
            @ApiResponse(code = 400, message = "The course has been already finished or illegal account role on the course"),
            @ApiResponse(code = 404, message = "Course not found", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/courses/{course-id}/themes/{theme-id}/lessons", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addLessonToCourse(PRINCIPAL user,
                           @PathVariable("course-id") UUID courseId,
                           @PathVariable("theme-id") UUID themeId,
                           @RequestBody LessonCreationForm form);

    @ApiOperation(value = "Attach files to lesson")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Attached files to lesson successfully"),
            @ApiResponse(code = 400, message = "The course has been already finished or Illegal account role on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Lesson not found", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/courses/{course-id}/lessons/{lesson-id}/files", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void attachFilesToLesson(PRINCIPAL user,
                             @PathVariable("course-id") UUID courseId,
                             @PathVariable("lesson-id") UUID lessonId,
                             @RequestParam("mp-files") MultipartFile[] multipartFiles);

    @ApiOperation(value = "Get lesson")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got lesson successfully", response = LessonResponse.class),
            @ApiResponse(code = 404, message = "Lesson not found", response = ExceptionMessage.class)
    })
    @GetMapping(value = "/lessons/{lesson-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    LessonResponse getLesson(PRINCIPAL user, @PathVariable("lesson-id") UUID lessonId);
}

