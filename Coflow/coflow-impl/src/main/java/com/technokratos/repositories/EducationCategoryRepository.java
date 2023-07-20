package com.technokratos.repositories;

import com.technokratos.dto.enums.EducationCategory;
import com.technokratos.models.EducationCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EducationCategoryRepository extends JpaRepository<EducationCategoryEntity, UUID> {

    Optional<EducationCategoryEntity> findByName(EducationCategory educationCategory);
}
