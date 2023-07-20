package com.technokratos.repositories;

import com.technokratos.dto.enums.CourseType;
import com.technokratos.models.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    Page<CourseEntity> findAllByCourseType_Name(CourseType type, Pageable pageable);

    Page<CourseEntity> findAllByCourseType_NameAndNameLikeAndLanguage_Name(CourseType type, String name,
                                                                           String language, Pageable pageable);

    Page<CourseEntity> findAllByCourseType_NameAndNameLike(CourseType type, String name, Pageable pageable);

    Page<CourseEntity> findAllByCourseType_NameAndLanguage_Name(CourseType type, String language, Pageable pageable);
}
