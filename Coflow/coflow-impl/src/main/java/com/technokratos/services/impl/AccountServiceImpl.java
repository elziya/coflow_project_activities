package com.technokratos.services.impl;

import com.technokratos.dto.enums.State;
import com.technokratos.dto.request.AccountNamesRequest;
import com.technokratos.dto.request.AccountPasswordRequest;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.StudentExtendedResponse;
import com.technokratos.exceptions.CoflowAccessDeniedException;
import com.technokratos.exceptions.CoflowAccountIllegalStateException;
import com.technokratos.exceptions.CoflowAccountNotFoundException;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.EducationCategoryEntity;
import com.technokratos.repositories.AccountRepository;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.AccountService;
import com.technokratos.util.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountEntity findAccountById(UUID id) {
        return accountRepository.findById(id).orElseThrow(CoflowAccountNotFoundException::new);
    }

    @Override
    public AccountEntity getAccountByEmail(String email) {
        return accountRepository.findByEmail(email).orElseThrow(CoflowAccountNotFoundException::new);
    }

    @Override
    public void confirmAccount(UUID id) {
        AccountEntity account = findAccountById(id);

        if (!State.CONFIRMED.equals(account.getState())) {
            account.setState(State.CONFIRMED);
            accountRepository.save(account);
        } else {
            throw new CoflowAccountIllegalStateException();
        }
    }

    @Override
    public AccountExtendedResponse updateAccount(AccountUserDetails user, UUID userId,
                                                 AccountNamesRequest newData) {
        checkAccountId(user, userId);
        AccountEntity account = accountRepository.findById(userId).orElseThrow(CoflowAccountNotFoundException::new);

        account.setFirstName(newData.getFirstName());
        account.setLastName(newData.getLastName());

        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public AccountExtendedResponse updateAccount(AccountUserDetails user, UUID userId,
                                                 AccountPasswordRequest newData) {
        checkAccountId(user, userId);
        AccountEntity account = accountRepository.findById(userId).orElseThrow(CoflowAccountNotFoundException::new);

        account.setPasswordHash(passwordEncoder.encode(newData.getPassword()));

        return accountMapper.toResponse(accountRepository.save(account));
    }

    @Override
    public AccountExtendedResponse getAccount(UUID userId) {
        return accountMapper.toResponse(accountRepository.findById(userId).orElseThrow(CoflowAccountNotFoundException::new));
    }

    @Override
    public StudentExtendedResponse mapAccountToStudent(AccountEntity account, EducationCategoryEntity category) {
        StudentExtendedResponse studentExtendedResponse = accountMapper.toStudentExtendedResponse(account);
        studentExtendedResponse.setEducationCategory(category.getName());
        return studentExtendedResponse;
    }

    private void checkAccountId(AccountUserDetails user, UUID userId){
        if (!user.getAccount().getId().equals(userId)){
            throw new CoflowAccessDeniedException("Access to updating account is denied");
        }
    }
}

