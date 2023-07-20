package com.technokratos.repositories;

import com.technokratos.models.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, UUID> {

    Optional<FeedbackEntity> findByAuthor_IdAndAndCourse_Id(UUID authorId, UUID courseId);
}
