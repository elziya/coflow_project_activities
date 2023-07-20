package com.technokratos.services;

import com.technokratos.dto.request.FeedbackForm;
import com.technokratos.models.AccountEntity;

import java.util.UUID;

public interface FeedbackService {

    UUID addCourseFeedback(AccountEntity account, UUID courseId, FeedbackForm form);
}
