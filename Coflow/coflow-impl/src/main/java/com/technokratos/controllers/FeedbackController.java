package com.technokratos.controllers;

import com.technokratos.api.FeedbackApi;
import com.technokratos.dto.request.FeedbackForm;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FeedbackController implements FeedbackApi<AccountUserDetails> {

    private final FeedbackService feedbackService;

    @Override
    public UUID giveCourseFeedback(@AuthenticationPrincipal AccountUserDetails user, UUID courseId, FeedbackForm form) {
        return feedbackService.addCourseFeedback(user.getAccount(), courseId, form);
    }
}

