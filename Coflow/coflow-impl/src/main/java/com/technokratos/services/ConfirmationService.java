package com.technokratos.services;

import com.technokratos.dto.request.AccountConfirmForm;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.ConfirmationCodeEntity;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ConfirmationService {

    Boolean confirmEmail(AccountConfirmForm confirmForm);

    void sendConfirmEmail(AccountEntity account, ConfirmationCodeEntity code);

    UUID generateNewConfirmCode();

    boolean isCodeExpired(ConfirmationCodeEntity code, int expires);

    UUID sendNewAccountConfirmCode(String email);

    ConfirmationCodeEntity generateNewAccountConfirmCode(UUID accountId);
}

