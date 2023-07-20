package com.technokratos.controllers;

import com.technokratos.api.OAuthAPI;
import com.technokratos.dto.oAuth.VkRequest;
import com.technokratos.dto.response.TokenCoupleResponse;
import com.technokratos.services.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController implements OAuthAPI {

    private final OAuthService oAuthService;

    @Override
    public VkRequest auth() {
        return oAuthService.getVkRequest();
    }

    @Override
    public TokenCoupleResponse receiveCode(String code) {
        return oAuthService.getTokenCouple();
    }
}
