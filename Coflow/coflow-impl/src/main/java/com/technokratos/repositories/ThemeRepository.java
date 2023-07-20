package com.technokratos.repositories;

import com.technokratos.models.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ThemeRepository extends JpaRepository<ThemeEntity, UUID> {
}
