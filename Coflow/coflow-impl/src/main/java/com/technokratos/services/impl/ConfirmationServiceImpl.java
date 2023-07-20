package com.technokratos.services.impl;

import com.technokratos.dto.enums.CodeType;
import com.technokratos.dto.enums.State;
import com.technokratos.dto.request.AccountConfirmForm;
import com.technokratos.exceptions.*;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.ConfirmationCodeEntity;
import com.technokratos.repositories.ConfirmationCodeRepository;
import com.technokratos.services.AccountService;
import com.technokratos.services.ConfirmationService;
import com.technokratos.util.email.EmailGenerator;
import com.technokratos.util.email.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.technokratos.consts.CoflowCommonConstants.ACCOUNT_CONFIRM_CODE_TIME_VALID;
import static com.technokratos.consts.CoflowMessageConstants.REGISTRATION_EMAIL_TITLE;

@RequiredArgsConstructor
@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private final AccountService accountService;

    private final ConfirmationCodeRepository confirmationCodeRepository;

    private final EmailUtil emailUtil;

    private final EmailGenerator emailGenerator;

    @Override
    public Boolean confirmEmail(AccountConfirmForm confirmForm) {
        AccountEntity account = accountService.getAccountByEmail(confirmForm.getEmail());

        ConfirmationCodeEntity code = account.getCodes().stream()
                .filter(c -> c.getCode().equals(confirmForm.getCode()))
                .findAny()
                .orElseThrow(CoflowIllegalConfirmCodeException::new);

        if (!isCodeExpired(code, ACCOUNT_CONFIRM_CODE_TIME_VALID)) {
            accountService.confirmAccount(account.getId());

            Set<ConfirmationCodeEntity> accConfirmCodes = account.getCodes().stream()
                    .filter(c -> CodeType.ACCOUNT_CONFIRM.equals(c.getType()))
                    .collect(Collectors.toSet());
            confirmationCodeRepository.deleteAll(accConfirmCodes);

            return true;
        }

        throw new CoflowConfirmCodeExpiredException();
    }

    @Override
    public boolean isCodeExpired(ConfirmationCodeEntity code, int expires) {
        boolean isExpired = false;

        Instant curTime = Instant.now();
        if (Duration.between(code.getCreateDate(), curTime).toHours() > expires) {
            isExpired = true;
        }
        return isExpired;
    }

    @Override
    public UUID sendNewAccountConfirmCode(String email) {
        UUID accountId = accountService.getAccountByEmail(email).getId();

        ConfirmationCodeEntity newCode = generateNewAccountConfirmCode(accountId);
        sendConfirmEmail(accountService.findAccountById(accountId), newCode);

        return newCode.getCode();
    }

    @Override
    public ConfirmationCodeEntity generateNewAccountConfirmCode(UUID accountId) {
        AccountEntity account = accountService.findAccountById(accountId);

        if (!State.CONFIRMED.equals(account.getState())) {
           Set<ConfirmationCodeEntity> accConfirmCodes = account.getCodes().stream()
                    .filter(c -> CodeType.ACCOUNT_CONFIRM.equals(c.getType())
                            && !isCodeExpired(c, ACCOUNT_CONFIRM_CODE_TIME_VALID))
                    .collect(Collectors.toSet());

           if (!accConfirmCodes.isEmpty()) {
               throw new CoflowAlreadyExistingConfirmCodeException();
           }

           ConfirmationCodeEntity newCode = ConfirmationCodeEntity.builder()
                   .account(account)
                   .code(generateNewConfirmCode())
                   .type(CodeType.ACCOUNT_CONFIRM)
                   .build();

           confirmationCodeRepository.save(newCode);

           return newCode;
        }

        throw new CoflowAccountIllegalStateException();
    }

    @Override
    public UUID generateNewConfirmCode() {
        return UUID.randomUUID();
    }

    @Override
    public void sendConfirmEmail(AccountEntity account, ConfirmationCodeEntity code) {
        String confirmMail = emailGenerator.generateConfirmationMail((account.getFirstName() + " " + account.getLastName()),
                String.valueOf(code.getCode()));
        emailUtil.sendEmail(account.getEmail(), REGISTRATION_EMAIL_TITLE, confirmMail, null);
    }
}

