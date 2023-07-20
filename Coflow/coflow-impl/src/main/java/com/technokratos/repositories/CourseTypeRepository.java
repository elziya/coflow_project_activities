package com.technokratos.repositories;

import com.technokratos.models.CourseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourseTypeRepository extends JpaRepository<CourseTypeEntity, UUID> {

    Optional<CourseTypeEntity> findByName(String name);
}
