package com.technokratos.repositories;

import com.technokratos.models.ConfirmationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCodeEntity, UUID> {
}
