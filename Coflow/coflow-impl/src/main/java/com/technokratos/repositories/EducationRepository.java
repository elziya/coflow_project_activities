package com.technokratos.repositories;

import com.technokratos.models.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<EducationEntity, UUID> {

    Optional<EducationEntity> findByAccount_IdAndCourse_Id(UUID accountId, UUID courseId);

    List<EducationEntity> findByCourse_Id(UUID courseId);
}
