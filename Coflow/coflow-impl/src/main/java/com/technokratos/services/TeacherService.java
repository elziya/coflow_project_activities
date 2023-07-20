package com.technokratos.services;

import com.technokratos.dto.request.TeacherRequest;
import com.technokratos.dto.response.TeacherExtendedResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.models.CourseEntity;
import com.technokratos.models.TeacherEntity;
import com.technokratos.security.details.AccountUserDetails;

import java.util.Set;
import java.util.UUID;

public interface TeacherService {

    UUID addTeacher(AccountUserDetails user, TeacherRequest teacher);

    TeacherResponse updateTeacher(AccountUserDetails user, UUID teacherId, TeacherRequest newData);

    TeacherExtendedResponse getTeacher(UUID teacherId);

    TeacherEntity getTeacherByAccountId(UUID accountId);

    Set<TeacherResponse> getCourseTeachers(UUID courseId);
}
