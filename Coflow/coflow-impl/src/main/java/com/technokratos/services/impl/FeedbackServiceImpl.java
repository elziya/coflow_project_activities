package com.technokratos.services.impl;

import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.dto.request.FeedbackForm;
import com.technokratos.exceptions.CoflowIllegalEducationCategoryStateException;
import com.technokratos.exceptions.CoflowCourseFeedbackAlreadyExistingException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.FeedbackEntity;
import com.technokratos.repositories.FeedbackRepository;
import com.technokratos.services.CourseService;
import com.technokratos.services.EducationService;
import com.technokratos.services.FeedbackService;
import com.technokratos.util.mapper.FeedbackMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final CourseService courseService;

    private final EducationService educationService;

    private final FeedbackRepository feedbackRepository;

    private final FeedbackMapper feedbackMapper;

    @Override
    public UUID addCourseFeedback(AccountEntity account, UUID courseId, FeedbackForm form) {
        if (EducationCategory.FINISHED.equals(
                educationService.findEducationInfo(account, courseService.getCourseById(courseId)).getCategory().getName())) {
            if (!feedbackRepository.findByAuthor_IdAndAndCourse_Id(account.getId(), courseId).isPresent()) {
                FeedbackEntity feedback = feedbackMapper.toEntity(form);
                feedback.setAuthor(account);
                feedback.setCourse(courseService.getCourseById(courseId));
                feedback.setCreateDate(Instant.now());
                return feedbackRepository.save(feedback).getId();
            } else {
                throw new CoflowCourseFeedbackAlreadyExistingException();
            }
        } else {
            throw new CoflowIllegalEducationCategoryStateException("The account can't give feedback about an unfinished course");
        }
    }
}

