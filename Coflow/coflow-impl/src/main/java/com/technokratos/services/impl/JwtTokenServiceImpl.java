package com.technokratos.services.impl;

import com.technokratos.dto.TokenCoupleDto;
import com.technokratos.dto.response.TokenCoupleResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.RefreshTokenEntity;
import com.technokratos.providers.JwtTokenProvider;
import com.technokratos.repositories.AccountRepository;
import com.technokratos.services.JwtTokenService;
import com.technokratos.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.technokratos.consts.CoflowConstants.BEARER;

@RequiredArgsConstructor
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final AccountRepository accountRepository;

    @Override
    public TokenCoupleResponse getTokenCouple(AccountEntity account) {
        String accessToken = jwtTokenProvider.generateAccessToken(account);

        return TokenCoupleResponse.builder()
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(jwtTokenProvider.generateAccessToken(account)))
                .refreshToken(refreshTokenService.generateRefreshToken(account))
                .accessTokenExpirationDate(jwtTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto) {
        List<String> roles = jwtTokenProvider
                .getRolesFromAccessToken(
                        tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY),
                        false);
        RefreshTokenEntity verifiedRefreshToken = refreshTokenService.verifyRefreshTokenExpiration(
                tokenCoupleDto.getRefreshToken(), roles);

        String accessToken = jwtTokenProvider.generateAccessToken(
                accountRepository.getById(UUID.fromString(jwtTokenProvider.getSubjectFromAccessToken(
                        tokenCoupleDto.getAccessToken().replace(BEARER.concat(StringUtils.SPACE), StringUtils.EMPTY)))));

        return TokenCoupleResponse.builder()
                .refreshToken(String.valueOf(verifiedRefreshToken.getId()))
                .accessToken(BEARER.concat(StringUtils.SPACE).concat(accessToken))
                .accessTokenExpirationDate(jwtTokenProvider.getExpirationDateFromAccessToken(accessToken))
                .build();
    }
}
