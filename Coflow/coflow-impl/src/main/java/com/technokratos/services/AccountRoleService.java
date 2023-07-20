package com.technokratos.services;

import com.technokratos.dto.enums.Role;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.dto.response.TeacherResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.AccountRoleEntity;
import com.technokratos.models.CourseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface AccountRoleService {

    void changeAccountsRoleOnCourse(AccountEntity account, CourseEntity course, Role role);

    void setSiteAccountRole(AccountEntity account, Role role);

    AccountRoleEntity getAccountOnCourseRoleInfo(UUID accountId, UUID courseId);

    Optional<AccountRoleEntity> findAccountOnCourseRoleInfo(UUID accountId, UUID courseId);

    Set<CourseResponse> getTeachingCourses(UUID accountId);

    void deleteAccountCourseRoleInfo(AccountEntity account, CourseEntity course);

    List<AccountRoleEntity> getCoursesRolesInfo(UUID courseId);
}
