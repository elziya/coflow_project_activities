package com.technokratos.repositories;

import com.technokratos.models.AccountRefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRefreshTokenRepository extends JpaRepository<AccountRefreshTokenEntity, UUID> {
}
