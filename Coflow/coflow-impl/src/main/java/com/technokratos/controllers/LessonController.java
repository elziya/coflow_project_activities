package com.technokratos.controllers;

import com.technokratos.api.LessonApi;
import com.technokratos.dto.request.LessonCreationForm;
import com.technokratos.dto.response.LessonResponse;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.LessonService;
import liquibase.pro.packaged.A;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class LessonController implements LessonApi<AccountUserDetails> {

    private final LessonService lessonService;

    @Override
    public UUID addLessonToCourse(@AuthenticationPrincipal AccountUserDetails user, UUID courseId, UUID themeId, LessonCreationForm form) {
        return lessonService.addLesson(user.getAccount().getId(), courseId, themeId, form);
    }

    @Override
    public void attachFilesToLesson(@AuthenticationPrincipal AccountUserDetails user, UUID courseId, UUID lessonId, MultipartFile[] multipartFiles) {
        lessonService.attachFilesToLesson(user.getAccount(), courseId, lessonId, multipartFiles);
    }

    @Override
    public LessonResponse getLesson(@AuthenticationPrincipal AccountUserDetails user,  UUID lessonId) {
        return lessonService.getLesson(user.getAccount(), lessonId);
    }
}

