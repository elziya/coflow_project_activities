package com.technokratos.services.impl;

import com.technokratos.dto.enums.CourseType;
import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.dto.enums.Role;
import com.technokratos.dto.response.StudentExtendedResponse;
import com.technokratos.exceptions.CoflowEducationInfoNotFoundException;
import com.technokratos.exceptions.CoflowIllegalCourseAccessCodeException;
import com.technokratos.exceptions.CoflowIllegalCourseEducationStateException;
import com.technokratos.models.*;
import com.technokratos.repositories.EducationRepository;
import com.technokratos.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EducationServiceImpl implements EducationService {

    private final AccountService accountService;

    private final AccountRoleService accountRoleService;

    private final EducationCategoryService educationCategoryService;

    private final EducationRepository educationRepository;

    private final QueryService queryService;

    @Override
    public void addStudentToCourse(AccountEntity account, CourseEntity course, UUID accessCode) {
        if (CourseType.PRIVATE.equals(course.getCourseType().getName())) {
            if (!course.getAccessCode().equals(accessCode)) {
                throw new CoflowIllegalCourseAccessCodeException();
            }
        }

        Optional<EducationEntity> education = educationRepository.findByAccount_IdAndCourse_Id(account.getId(), course.getId());
        if (education.isPresent()) {
            if (EducationCategory.DESIRED.equals(education.get().getCategory().getName())) {
                deleteAccountCourseEducation(account, course);
            }
        }

        if (!accountRoleService.findAccountOnCourseRoleInfo(account.getId(), course.getId()).isPresent()) {
            accountRoleService.changeAccountsRoleOnCourse(account, course, Role.STUDENT);
            addAccountCourseEducation(account, course, EducationCategory.PROCESS);
        } else {
            throw new CoflowIllegalCourseEducationStateException("There is already info about the role of " +
                    "the account in this course");
        }
    }

    @Override
    public void addAccountCourseEducation(AccountEntity account, CourseEntity course, EducationCategory educationCategory) {
        if (!educationRepository.findByAccount_IdAndCourse_Id(account.getId(), course.getId()).isPresent()) {
            EducationEntity education = EducationEntity.builder()
                    .account(account)
                    .course(course)
                    .category(educationCategoryService.getEducationCategoryByName(educationCategory))
                    .build();

            educationRepository.save(education);
        } else {
            throw new CoflowIllegalCourseEducationStateException("This is already account-course education info");
        }
    }

    @Override
    public void deleteAccountCourseEducation(AccountEntity account, CourseEntity course) {
        educationRepository.delete(
                educationRepository.findByAccount_IdAndCourse_Id(account.getId(), course.getId())
                        .orElseThrow((Supplier<RuntimeException>) ()
                                -> new CoflowIllegalCourseEducationStateException("No such account-course education"))
        );
    }

    @Override
    public void deleteStudentFromCourse(AccountEntity account, CourseEntity course) {
        if (accountRoleService.findAccountOnCourseRoleInfo(account.getId(), course.getId()).isPresent()) {
            deleteAccountCourseEducation(account, course);
            accountRoleService.deleteAccountCourseRoleInfo(account, course);
        } else {
            throw new CoflowIllegalCourseEducationStateException("The account isn't participating in the course");
        }
    }

    @Override
    public EducationEntity findEducationInfo(AccountEntity account, CourseEntity course) {
        return educationRepository.findByAccount_IdAndCourse_Id(account.getId(), course.getId())
                .orElseThrow(CoflowEducationInfoNotFoundException::new);
    }

    @Override
    public Set<StudentExtendedResponse> getCourseStudents(UUID courseId) {
        return educationRepository.findByCourse_Id(courseId).stream()
                .filter(e -> !EducationCategory.DESIRED.equals(e.getCategory().getName()))
                .map(e -> accountService.mapAccountToStudent(e.getAccount(), e.getCategory()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<EducationEntity> findByCourseId(UUID id) {
        return Set.copyOf(educationRepository.findByCourse_Id(id));
    }

    @Override
    public void setCourseIsFinished(UUID accountId, UUID courseId) {
        Optional<EducationEntity> educationOptional = educationRepository.findByAccount_IdAndCourse_Id(accountId, courseId);
        if (educationOptional.isPresent()) {
            EducationEntity education = educationOptional.get();
            if (!EducationCategory.DESIRED.equals(education.getCategory().getName())) {
                education.setCategory(educationCategoryService.getEducationCategoryByName(EducationCategory.FINISHED));
                educationRepository.save(education);
            }
        }
    }

    @Override
    public Boolean checkCourseIsFinished(UUID accountId, CourseEntity course) {
        boolean isFinished = false;
        if (course.getIsFinished()) {
            List<UUID> queriedResources = queryService.getAllAccountsResourcesQueries(accountId).stream()
                    .map(QueryEntity::getResourceId)
                    .collect(Collectors.toList());
            List<UUID> courseLessons = getAllCourseLessonsId(course);
            if (queriedResources.containsAll(courseLessons)) {
                isFinished = true;
            }
        }
        return isFinished;
    }

    @Override
    public void updateQueriesInfo(CourseEntity course, QueryEntity query) {
        Optional<EducationEntity> education = educationRepository.findByAccount_IdAndCourse_Id(query.getAccountId(), course.getId());
        if (education.isPresent()) {
            if (EducationCategory.DESIRED.equals(education.get().getCategory().getName())) {
                return;
            }
            if (!queryService.checkResourceQueryExists(query.getAccountId(), course.getId())) {
                queryService.saveQueryInfo(query);
            }
            if (checkCourseIsFinished(query.getAccountId(), course)) {
                setCourseIsFinished(query.getAccountId(), course.getId());
            }
        }
    }

    @Override
    public Instant getCourseFinishingDate(AccountEntity account, CourseEntity course) {
        EducationEntity education = educationRepository.findByAccount_IdAndCourse_Id(account.getId(), course.getId())
                .orElseThrow(CoflowEducationInfoNotFoundException::new);
        if (EducationCategory.FINISHED.equals(education.getCategory().getName())) {
            List<QueryEntity> lessonQueries = queryService.getAllAccountsResourcesQueries(account.getId());
            List<UUID> courseLessons = getAllCourseLessonsId(course);
            lessonQueries = lessonQueries.stream()
                    .filter(q -> courseLessons.contains(q.getResourceId()))
                    .sorted(Comparator.comparing(QueryEntity::getDate))
                    .collect(Collectors.toList());
            return lessonQueries.get(0).getDate();
        } else {
            throw new CoflowIllegalCourseEducationStateException("The course hasn't been finished yet");
        }
    }

    private List<UUID> getAllCourseLessonsId(CourseEntity course) {
        List<UUID> courseLessons = new ArrayList<>();
        course.getThemes().forEach(t -> {
            courseLessons.addAll(
                    t.getLessons().stream()
                            .map(AbstractEntity::getId)
                            .collect(Collectors.toList())
            );
        });
        return courseLessons;
    }
}

