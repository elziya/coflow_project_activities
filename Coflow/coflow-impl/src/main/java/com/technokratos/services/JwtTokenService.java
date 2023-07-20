package com.technokratos.services;

import com.technokratos.dto.TokenCoupleDto;
import com.technokratos.dto.response.TokenCoupleResponse;
import com.technokratos.models.AccountEntity;

public interface JwtTokenService {

    TokenCoupleResponse getTokenCouple(AccountEntity account);

    TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto);
}
