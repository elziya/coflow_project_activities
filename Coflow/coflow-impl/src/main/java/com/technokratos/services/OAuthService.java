package com.technokratos.services;


import com.technokratos.dto.oAuth.VkRequest;
import com.technokratos.dto.response.TokenCoupleResponse;
import com.technokratos.models.AccountEntity;

public interface OAuthService {

    AccountEntity authCode(String code);

    TokenCoupleResponse getTokenCouple();

    VkRequest getVkRequest();
}
