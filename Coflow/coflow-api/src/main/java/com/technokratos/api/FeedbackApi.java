package com.technokratos.api;

import com.technokratos.dto.request.FeedbackForm;
import com.technokratos.dto.response.ExceptionMessage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/v1/")
public interface FeedbackApi<PRINCIPAL>  {

    @ApiOperation(value = "Give finished course feedback")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Add feedback successfully", response = UUID.class),
            @ApiResponse(code = 400, message = "Illegal account's education state on the course or there is already a feedback from this account", response = ExceptionMessage.class),
            @ApiResponse(code = 404, message = "Such course doesn't exist or such account's course education info doesn't exist", response = ExceptionMessage.class)
    })
    @PostMapping(value = "/courses/{course-id}/feedbacks", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID giveCourseFeedback(PRINCIPAL user, @PathVariable("course-id") UUID courseId, @RequestBody FeedbackForm form);
}

