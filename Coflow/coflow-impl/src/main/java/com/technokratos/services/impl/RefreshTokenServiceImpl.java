package com.technokratos.services.impl;

import com.technokratos.exceptions.CoflowTokenRefreshException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.AccountRefreshTokenEntity;
import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.repositories.AccountRefreshTokenRepository;
import com.technokratos.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final AccountRefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.expiration.refresh.millis}")
    private long expirationRefreshInMillis;

    @Override
    public String generateRefreshToken(AccountEntity account) {

        return refreshTokenRepository.save(
                AccountRefreshTokenEntity.builder()
                        .expiryDate(Instant.now().plusMillis(expirationRefreshInMillis))
                        .account(account)
                        .build()
                )
                .getId().toString();
    }

    @Override
    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken, List<String> roles) {
        return refreshTokenRepository.findById(UUID.fromString(refreshToken)).map(token -> {

            refreshTokenRepository.delete(token);
            if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
                throw new CoflowTokenRefreshException(String.valueOf(token.getId()), "The refresh token has expired");
            }
            return refreshTokenRepository.save(
                    AccountRefreshTokenEntity.builder()
                            .expiryDate(Instant.now().plusMillis(expirationRefreshInMillis))
                            .account(token.getAccount())
                            .build());

        }).orElseThrow(() -> {
            throw new CoflowTokenRefreshException(refreshToken, "Token doesn't exist");
        });
    }
}
