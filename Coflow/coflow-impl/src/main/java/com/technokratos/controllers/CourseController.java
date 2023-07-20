package com.technokratos.controllers;

import com.technokratos.api.CourseApi;
import com.technokratos.dto.request.*;
import com.technokratos.dto.response.CourseExtendedResponse;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CourseController implements CourseApi<AccountUserDetails> {

    private final CourseService courseService;

    @Override
    public CourseExtendedResponse getCourse(UUID courseId) {
        return courseService.getCourse(courseId);
    }

    @Override
    public UUID createCourse(@AuthenticationPrincipal AccountUserDetails user, CourseExtendedCreationForm course) {
        return courseService.createCourse(user.getAccount(), course);
    }

    @Override
    public void addTeacherToCourse(@AuthenticationPrincipal AccountUserDetails user, CourseTeacherRequest teacher, UUID course) {
        courseService.addTeacherToCourse(user.getAccount(), course, teacher);
    }

    @Override
    public void finishCourse(@AuthenticationPrincipal AccountUserDetails user, UUID courseId) {
        courseService.finishCourse(user.getAccount(), courseId);
    }

    @Override
    public UUID addThemeToCourse(@AuthenticationPrincipal AccountUserDetails user, UUID courseId, ThemeCreationForm form) {
        return courseService.addTheme(user.getAccount(), courseId, form);
    }

    @Override
    public void joinCourse(@AuthenticationPrincipal AccountUserDetails user, UUID courseId, JoinCourseForm form) {
        courseService.joinCourse(user.getAccount(), courseId, form);
    }

    @Override
    public void leaveCourse(@AuthenticationPrincipal AccountUserDetails user, UUID courseId) {
        courseService.leaveCourse(user.getAccount(), courseId);
    }

    @Override
    public void addCourseToDesired(@AuthenticationPrincipal AccountUserDetails user, UUID courseId) {
        courseService.addCourseToDesired(user.getAccount(), courseId);
    }

    @Override
    public void deleteCourseFromDesired(@AuthenticationPrincipal AccountUserDetails user, UUID courseId) {
        courseService.deleteCourseFromDesired(user.getAccount(), courseId);
    }

    @Override
    public void getFinishedCourseCertificate(@AuthenticationPrincipal AccountUserDetails user, UUID courseId) {
        courseService.sendFinishedCourseCertificate(user.getAccount(), courseId);
    }
}

