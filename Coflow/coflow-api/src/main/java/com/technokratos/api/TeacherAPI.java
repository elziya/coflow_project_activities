package com.technokratos.api;

import com.technokratos.dto.request.TeacherRequest;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.ExceptionMessage;
import com.technokratos.dto.response.TeacherExtendedResponse;
import com.technokratos.dto.response.TeacherResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/teachers")
public interface TeacherAPI<PRINCIPAL> {

    @ApiOperation(value = "Becoming and adding teacher's information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added",
                    response = AccountExtendedResponse.class),
            @ApiResponse(code = 400, message = "Teacher already exists",
                    response = ExceptionMessage.class)
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addTeacher(PRINCIPAL user, @RequestBody TeacherRequest teacher);

    @ApiOperation(value = "Update teacher's information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated",
                    response = AccountExtendedResponse.class),
            @ApiResponse(code = 404, message = "Teacher not found",
                    response = ExceptionMessage.class),
            @ApiResponse(code = 403, message = "Access to updating teacher is denied",
                    response = ExceptionMessage.class)
    })
    @PutMapping(value = "/{teacher-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TeacherResponse updateTeacher(PRINCIPAL user, @PathVariable("teacher-id") UUID teacherId, @RequestBody TeacherRequest newData);

    @ApiOperation(value = "Get teacher's information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully got teacher's information",
                    response = AccountExtendedResponse.class),
            @ApiResponse(code = 404, message = "Teacher not found",
                    response = ExceptionMessage.class)
    })
    @GetMapping(value = "/{teacher-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TeacherExtendedResponse getTeacher(@PathVariable("teacher-id") UUID teacherId);
}
