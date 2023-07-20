package com.technokratos.controllers;

import com.technokratos.api.AccountAPI;
import com.technokratos.dto.request.AccountNamesRequest;
import com.technokratos.dto.request.AccountPasswordRequest;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountAPI<AccountUserDetails> {

    private final AccountService accountService;

    @Override
    public AccountExtendedResponse updateAccount(@AuthenticationPrincipal AccountUserDetails user, UUID userId,
                                                 AccountNamesRequest newData) {
        return accountService.updateAccount(user, userId, newData);
    }

    @Override
    public AccountExtendedResponse updateAccount(@AuthenticationPrincipal AccountUserDetails user, UUID userId,
                                                 AccountPasswordRequest newData) {
        return accountService.updateAccount(user, userId, newData);
    }

    @Override
    public AccountExtendedResponse getAccount(UUID userId) {
        return accountService.getAccount(userId);
    }
}
