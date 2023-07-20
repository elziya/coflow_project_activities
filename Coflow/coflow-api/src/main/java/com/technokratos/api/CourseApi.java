package com.technokratos.api;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.CourseExtendedResponse;
import com.technokratos.dto.response.ExceptionMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/courses")
public interface CourseApi<PRINCIPAL> {

    @ApiOperation(value = "Get course info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got course successfully", response = CourseExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not existing course", response = ExceptionMessage.class)
    })
    @GetMapping(value = "/{course-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CourseExtendedResponse getCourse(@PathVariable("course-id") UUID courseId);

    @ApiOperation(value = "Create course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Created course successfully", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad course data or account isn't a teacher", response = ExceptionMessage.class)
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createCourse(PRINCIPAL user, @RequestBody CourseExtendedCreationForm course);

    @ApiOperation(value = "Add teacher to course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add teacher successfully"),
            @ApiResponse(code = 400, message = "Illegal account role on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such teacher doesn't exist", response = ExceptionMessage.class)
    })
    @PutMapping(value = "/{course-id}/teachers", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void addTeacherToCourse(PRINCIPAL user, @RequestBody CourseTeacherRequest teacher, @PathVariable("course-id") UUID course);

    @ApiOperation(value = "Finish course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Finished course successfully"),
            @ApiResponse(code = 400, message = "Illegal account role on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist", response = ExceptionMessage.class)
    })
    @PutMapping(value = "/{course-id}/finished", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void finishCourse(PRINCIPAL user, @PathVariable("course-id") UUID courseId);

    @ApiOperation(value = "Add theme to course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add theme successfully", response = UUID.class),
            @ApiResponse(code = 400, message = "Illegal account role on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/{course-id}/themes", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addThemeToCourse(PRINCIPAL user, @PathVariable("course-id") UUID courseId, @RequestBody ThemeCreationForm form);

    @ApiOperation(value = "Join course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Joined course successfully"),
            @ApiResponse(code = 400, message = "Illegal access code or illegal account's education state on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/{course-id}/students", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void joinCourse(PRINCIPAL user, @PathVariable("course-id") UUID courseId, @RequestBody JoinCourseForm form);

    @ApiOperation(value = "Leave course")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leave course successfully"),
            @ApiResponse(code = 400, message = "Illegal account's education state on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist or such account's course education info doesn't exist", response = ExceptionMessage.class)
    })
    @DeleteMapping(value = "/{course-id}/students", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void leaveCourse(PRINCIPAL user, @PathVariable("course-id") UUID courseId);

    @ApiOperation(value = "Add course to desired")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add course to desired successfully"),
            @ApiResponse(code = 400, message = "Illegal account's education state on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/{course-id}/desired-students", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void addCourseToDesired(PRINCIPAL user, @PathVariable("course-id") UUID courseId);

    @ApiOperation(value = "Delete course from desired")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted course from desired successfully"),
            @ApiResponse(code = 400, message = "Illegal account's education state on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist", response = ExceptionMessage.class)
    })
    @DeleteMapping(value = "/{course-id}/desired-students", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteCourseFromDesired(PRINCIPAL user, @PathVariable("course-id") UUID courseId);

    @ApiOperation(value = "Get finished course certificate")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Got finished course certificate successfully"),
            @ApiResponse(code = 400, message = "Illegal account's education state on the course", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist or such account's course education info doesn't exist", response = ExceptionMessage.class)
    })
    @GetMapping(value = "/{course-id}/certificate", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void getFinishedCourseCertificate(PRINCIPAL user, @PathVariable("course-id") UUID courseId);
}

