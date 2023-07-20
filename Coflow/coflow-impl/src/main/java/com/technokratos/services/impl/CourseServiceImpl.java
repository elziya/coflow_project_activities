package com.technokratos.services.impl;

import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.dto.enums.Role;
import com.technokratos.dto.request.*;
import com.technokratos.dto.response.CourseExtendedResponse;
import com.technokratos.dto.response.CourseResponse;
import com.technokratos.exceptions.*;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.AccountRoleEntity;
import com.technokratos.models.CourseEntity;
import com.technokratos.models.TeacherEntity;
import com.technokratos.repositories.CourseRepository;
import com.technokratos.services.*;
import com.technokratos.util.email.EmailGenerator;
import com.technokratos.util.email.EmailUtil;
import com.technokratos.util.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

import static com.technokratos.consts.CoflowMessageConstants.*;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final AccountRoleService accountRoleService;

    private final TeacherService teacherService;

    private final ThemeService themeService;

    private final EducationService educationService;

    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final EmailUtil emailUtil;

    private final EmailGenerator emailGenerator;

    @Override
    public UUID createCourse(AccountEntity account, CourseExtendedCreationForm courseRequest) {
        try {
            CourseEntity course = courseMapper.toEntity(courseRequest);
            course.setAccessCode(UUID.randomUUID());
            course.setIsFinished(false);

            TeacherEntity teacher = teacherService.getTeacherByAccountId(account.getId());

            course = courseRepository.save(course);
            UUID courseId = course.getId();

            accountRoleService.changeAccountsRoleOnCourse(teacher.getAccount(), course, Role.ADMIN);

            return courseId;
        } catch (DataIntegrityViolationException e) {
            throw new CoflowCourseCreationIllegalArgumentException(e.getMessage());
        } catch (CoflowTeacherNotFoundException e) {
            throw new CoflowCourseCreationIllegalArgumentException("A non-teacher account can't create a course");
        }
    }

    @Override
    public UUID addTheme(AccountEntity account, UUID courseId, ThemeCreationForm form) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new);

        if (!course.getIsFinished()) {
            AccountRoleEntity roleInfo = accountRoleService
                    .getAccountOnCourseRoleInfo(account.getId(), course.getId());

            if (Role.TEACHER.equals(roleInfo.getRole().getRole()) || Role.ADMIN.equals(roleInfo.getRole().getRole())) {
                return themeService.addTheme(course, form);
            } else {
                throw new CoflowNotPermittedRoleException("A non-teacher account can't create course's themes");
            }
        } else {
            throw new CoflowCourseIllegalStateException("Can't add new theme to already finished course");
        }
    }

    @Override
    public CourseEntity getCourseById(UUID courseId) {
        return courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new);
    }

    @Override
    public CourseResponse mapCourse(CourseEntity courseEntity) {
        return courseMapper.toResponse(courseEntity);
    }

    @Override
    public void joinCourse(AccountEntity account, UUID courseId, JoinCourseForm form) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new);
        educationService.addStudentToCourse(account, course, form.getAccessCode());
    }

    @Override
    public void leaveCourse(AccountEntity account, UUID courseId) {
        educationService.deleteStudentFromCourse(
                account, courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new)
        );
    }

    @Override
    public void addCourseToDesired(AccountEntity account, UUID courseId) {
        educationService.addAccountCourseEducation(
                account, courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new), EducationCategory.DESIRED
        );
    }

    @Override
    public void deleteCourseFromDesired(AccountEntity account, UUID courseId) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new);

        if (EducationCategory.DESIRED.equals(educationService.findEducationInfo(account, course).getCategory().getName())) {
            educationService.deleteAccountCourseEducation(account, course);
        } else {
            throw new CoflowIllegalCourseEducationStateException("The course was not marked as desired");
        }
    }

    @Override
    public CourseExtendedResponse getCourse(UUID courseId) {
        CourseExtendedResponse courseExtendedResponse =
                courseMapper.toExtendedResponse(
                        courseRepository.findById(courseId)
                                .orElseThrow(CoflowCourseNotFoundException::new)
                );

        courseExtendedResponse.setTeachers(teacherService.getCourseTeachers(courseId));
        courseExtendedResponse.setStudents(educationService.getCourseStudents(courseId));

        return courseExtendedResponse;
    }

    @Override
    public void finishCourse(AccountEntity account, UUID courseId) {
        AccountRoleEntity roleInfo = accountRoleService.getAccountOnCourseRoleInfo(account.getId(), courseId);
        if (Role.ADMIN.equals(roleInfo.getRole().getRole())) {
            CourseEntity course = courseRepository.getById(courseId);
            course.setIsFinished(true);
            courseRepository.save(course);
            course.getStudents().forEach(s -> {
                if (educationService.checkCourseIsFinished(s.getAccount().getId(), course)) {
                    educationService.setCourseIsFinished(s.getAccount().getId(), courseId);
                }
            });
        } else {
            throw new CoflowNotPermittedRoleException("A non-admin account can't finish course");
        }
    }

    @Override
    public void sendFinishedCourseCertificate(AccountEntity account, UUID courseId) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new);
        if (EducationCategory.FINISHED.equals(educationService.findEducationInfo(account, course).getCategory().getName())) {
            File certificate = emailGenerator.generateCertificate(account, course,
                    educationService.getCourseFinishingDate(account, course));
            emailUtil.sendEmail(account.getEmail(), CERTIFICATE_EMAIL_TITLE, CERTIFICATE_EMAIL_TEXT, certificate);
        } else {
            throw new CoflowIllegalEducationCategoryStateException("Course hasn't been finished yet");
        }
    }

    @Override
    public void addTeacherToCourse(AccountEntity account, UUID courseId, CourseTeacherRequest teacherRequest) {
        CourseEntity course = courseRepository.findById(courseId).orElseThrow(CoflowCourseNotFoundException::new);

        if (!course.getIsFinished()) {
            AccountRoleEntity roleInfo = accountRoleService
                    .getAccountOnCourseRoleInfo(account.getId(), course.getId());

            if (Role.ADMIN.equals(roleInfo.getRole().getRole())) {
                TeacherEntity teacher = teacherService.getTeacherByAccountId(teacherRequest.getTeacherId());
                try {
                    AccountRoleEntity teacherRoleInfo = accountRoleService.getAccountOnCourseRoleInfo(teacherRequest.getTeacherId(), courseId);
                    if (Role.ADMIN.equals(teacherRoleInfo.getRole().getRole()) || Role.TEACHER.equals(teacherRoleInfo.getRole().getRole())) {
                        throw new CoflowRoleInfoIllegalStateException("This account is already a teacher on the course");
                    } else {
                        accountRoleService.changeAccountsRoleOnCourse(teacher.getAccount(), course, Role.TEACHER);
                    }
                } catch (CoflowAccountRoleInfoNotFoundException e) {
                    accountRoleService.changeAccountsRoleOnCourse(teacher.getAccount(), course, Role.TEACHER);
                }
            } else {
                throw new CoflowNotPermittedRoleException("A non-admin account can't add course's teachers");
            }
        } else {
            throw new CoflowCourseIllegalStateException("Can't add new teacher to already finished course");
        }
    }
}

