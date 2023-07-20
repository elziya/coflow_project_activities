package com.technokratos.controllers;

import com.technokratos.api.JwtTokenApi;
import com.technokratos.dto.TokenCoupleDto;
import com.technokratos.dto.response.TokenCoupleResponse;
import com.technokratos.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class JwtTokenController implements JwtTokenApi {

    private final JwtTokenService jwtTokenService;

    @Override
    public TokenCoupleResponse updateTokens(TokenCoupleDto tokenCoupleDto) {
        return jwtTokenService.refreshAccessToken(tokenCoupleDto);
    }
}