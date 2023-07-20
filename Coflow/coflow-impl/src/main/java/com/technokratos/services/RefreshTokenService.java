package com.technokratos.services;

import com.technokratos.models.AccountEntity;
import com.technokratos.models.RefreshTokenEntity;

import java.util.List;

public interface RefreshTokenService {
    String generateRefreshToken(AccountEntity account);

    RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken, List<String> roles);
}
