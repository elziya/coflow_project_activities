package com.technokratos.services;

import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.dto.response.StudentExtendedResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.CourseEntity;
import com.technokratos.models.EducationEntity;
import com.technokratos.models.QueryEntity;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public interface EducationService {

    void addStudentToCourse(AccountEntity account, CourseEntity course, UUID accessCode);

    void addAccountCourseEducation(AccountEntity account, CourseEntity course, EducationCategory educationCategory);

    void deleteAccountCourseEducation(AccountEntity account, CourseEntity course);

    void deleteStudentFromCourse(AccountEntity account, CourseEntity course);

    EducationEntity findEducationInfo(AccountEntity account, CourseEntity course);

    Set<StudentExtendedResponse> getCourseStudents(UUID courseId);

    Set<EducationEntity> findByCourseId(UUID id);

    void setCourseIsFinished(UUID accountId, UUID courseId);

    Boolean checkCourseIsFinished(UUID accountId, CourseEntity course);

    void updateQueriesInfo(CourseEntity course, QueryEntity query);

    Instant getCourseFinishingDate(AccountEntity account, CourseEntity course);
}
