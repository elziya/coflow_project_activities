package com.technokratos.services.impl;

import com.technokratos.dto.enums.Role;
import com.technokratos.dto.request.LessonCreationForm;
import com.technokratos.dto.response.LessonResponse;
import com.technokratos.exceptions.*;
import com.technokratos.models.*;
import com.technokratos.repositories.LessonRepository;
import com.technokratos.services.*;
import com.technokratos.util.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.*;

import static com.technokratos.consts.CoflowCommonConstants.LESSON_MAX_NUMB_OF_FILES;

@RequiredArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {

    private final ThemeService themeService;

    private final FileInfoService fileInfoService;

    private final AccountRoleService accountRoleService;

    private final EducationService educationService;

    private final LessonRepository lessonRepository;

    private final LessonMapper lessonMapper;

    @Override
    public UUID addLesson(UUID accountId, UUID courseId, UUID themeId, LessonCreationForm form) {
        if (Role.ADMIN.equals(accountRoleService.getAccountOnCourseRoleInfo(accountId, courseId).getRole().getRole()) ||
                Role.TEACHER.equals(accountRoleService.getAccountOnCourseRoleInfo(accountId, courseId).getRole().getRole())) {
            ThemeEntity theme = themeService.getThemeById(themeId);
            if (!theme.getCourse().getIsFinished()) {
                if (theme.getCourse().getId().equals(courseId)) {
                    LessonEntity lesson = lessonMapper.toEntity(form);
                    lesson.setTheme(theme);
                    return lessonRepository.save(lesson).getId();
                } else {
                    throw new CoflowCourseNotFoundException();
                }
            } else {
                throw new CoflowCourseIllegalStateException("Can't add new lesson to already finished course");
            }
        } else {
            throw new CoflowNotPermittedRoleException("A non-teacher account can't add lessons to course");
        }
    }

    @Override
    public void attachFilesToLesson(AccountEntity user, UUID courseId, UUID lessonId, MultipartFile[] multipartFiles) {
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(CoflowLessonNotFoundException::new);

        if (!lesson.getTheme().getCourse().getIsFinished()) {
            if (Role.ADMIN.equals(accountRoleService.getAccountOnCourseRoleInfo(user.getId(), courseId).getRole().getRole()) ||
                    Role.TEACHER.equals(accountRoleService.getAccountOnCourseRoleInfo(user.getId(), courseId).getRole().getRole())) {
                Set<FileInfoEntity> materials = lesson.getMaterials();
                if (Objects.isNull(materials)) {
                    materials = new HashSet<>();
                }

                if (materials.size() + multipartFiles.length <= LESSON_MAX_NUMB_OF_FILES) {
                    Set<FileInfoEntity> updatedMaterials = materials;
                    Arrays.stream(multipartFiles).forEach(f -> updatedMaterials.add(fileInfoService.uploadFile(f)));
                    materials = updatedMaterials;
                } else {
                    throw new CoflowLessonMaterialsLimitException();
                }

                lesson.setMaterials(materials);
                lessonRepository.save(lesson);
            } else {
                throw new CoflowNotPermittedRoleException("A non-teacher account can't attach files");
            }
        } else {
            throw new CoflowCourseIllegalStateException("Can't attach lesson files to already finished course");
        }
    }

    @Override
    public LessonResponse getLesson(AccountEntity account, UUID lessonId) {
        LessonEntity lesson = lessonRepository.findById(lessonId).orElseThrow(CoflowLessonNotFoundException::new);
        educationService.updateQueriesInfo(lesson.getTheme().getCourse(), QueryEntity.builder()
                .accountId(account.getId())
                .resourceId(lessonId)
                .key(LessonEntity.class.getSimpleName())
                .date(Instant.now())
                .build());
        return lessonMapper.toResponse(lesson);
    }
}

