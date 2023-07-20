package com.technokratos.services;

import com.technokratos.dto.request.*;
import com.technokratos.dto.response.CourseExtendedResponse;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.CourseEntity;

import java.util.UUID;

public interface CourseService {

    UUID createCourse(AccountEntity account, CourseExtendedCreationForm courseRequest);

    UUID addTheme(AccountEntity account, UUID courseId, ThemeCreationForm form);

    CourseEntity getCourseById(UUID courseId);

    CourseResponse mapCourse(CourseEntity courseEntity);

    void joinCourse(AccountEntity account, UUID courseId, JoinCourseForm form);

    void addCourseToDesired(AccountEntity account, UUID courseId);

    void leaveCourse(AccountEntity account, UUID courseId);

    void deleteCourseFromDesired(AccountEntity account, UUID courseId);

    CourseExtendedResponse getCourse(UUID courseId);

    void finishCourse(AccountEntity account, UUID courseId);

    void sendFinishedCourseCertificate(AccountEntity account, UUID courseId);

    void addTeacherToCourse(AccountEntity account, UUID course, CourseTeacherRequest teacher);
}
