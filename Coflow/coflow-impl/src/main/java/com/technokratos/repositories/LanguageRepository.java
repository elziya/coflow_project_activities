package com.technokratos.repositories;

import com.technokratos.models.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LanguageRepository extends JpaRepository<LanguageEntity, UUID> {

    Optional<LanguageEntity> findByName(String name);
}
