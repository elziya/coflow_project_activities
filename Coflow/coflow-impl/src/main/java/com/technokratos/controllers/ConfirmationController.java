package com.technokratos.controllers;

import com.technokratos.api.ConfirmApi;
import com.technokratos.dto.request.AccountConfirmForm;
import com.technokratos.dto.request.AccountRequest;
import com.technokratos.dto.request.EmailConfirmCodeGenerationForm;
import com.technokratos.services.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ConfirmationController implements ConfirmApi {

    private final ConfirmationService confirmService;

    @Override
    public Boolean confirmAccount(AccountConfirmForm accountConfirmForm) {
        return confirmService.confirmEmail(accountConfirmForm);
    }

    @Override
    public UUID generateAccountConfirmCode(EmailConfirmCodeGenerationForm form) {
        return confirmService.sendNewAccountConfirmCode(form.getEmail());
    }
}

