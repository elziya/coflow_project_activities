package com.technokratos.services;

import com.technokratos.dto.request.LessonCreationForm;
import com.technokratos.dto.response.LessonResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.CourseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface LessonService {

    UUID addLesson(UUID id, UUID courseId, UUID themeId, LessonCreationForm forms);

    void attachFilesToLesson(AccountEntity user, UUID courseId, UUID lessonId, MultipartFile[] multipartFiles);

    LessonResponse getLesson(AccountEntity account, UUID lessonId);
}
