package com.technokratos.services.impl;

import com.technokratos.dto.enums.CodeType;
import com.technokratos.dto.enums.Role;
import com.technokratos.dto.enums.State;
import com.technokratos.dto.request.SignUpForm;
import com.technokratos.exceptions.CoflowAlreadyRegisteredEmailException;
import com.technokratos.exceptions.CoflowSignUpDataException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.ConfirmationCodeEntity;
import com.technokratos.repositories.AccountRepository;
import com.technokratos.services.AccountRoleService;
import com.technokratos.services.SignUpService;
import com.technokratos.util.email.EmailGenerator;
import com.technokratos.util.email.EmailUtil;
import com.technokratos.util.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

import static com.technokratos.consts.CoflowMessageConstants.REGISTRATION_EMAIL_TITLE;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountRoleService accountRoleService;

    private final ConfirmationServiceImpl confirmService;

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID signUp(SignUpForm signUpForm) {
        if (accountRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            throw new CoflowAlreadyRegisteredEmailException();
        }

        ConfirmationCodeEntity code = ConfirmationCodeEntity.builder()
                .code(confirmService.generateNewConfirmCode())
                .type(CodeType.ACCOUNT_CONFIRM)
                .build();

        AccountEntity account = accountMapper.signUpFormToEntity(signUpForm);
        account.setState(State.NOT_CONFIRMED);
        account.setCodes(new HashSet<>(Collections.singletonList(code)));
        account.setPasswordHash(passwordEncoder.encode(signUpForm.getPassword()));
        code.setAccount(account);

        UUID id = accountRepository.save(account).getId();
        confirmService.sendConfirmEmail(account, code);

        accountRoleService.setSiteAccountRole(account, Role.USER);

        return id;
    }
}

