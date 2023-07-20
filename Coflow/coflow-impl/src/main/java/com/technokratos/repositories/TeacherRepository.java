package com.technokratos.repositories;

import com.technokratos.models.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<TeacherEntity, UUID> {

    Optional<TeacherEntity> findById(UUID id);

    Optional<TeacherEntity> findByAccount_Id(UUID accountId);
}
