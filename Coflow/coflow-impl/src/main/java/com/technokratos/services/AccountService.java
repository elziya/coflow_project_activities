package com.technokratos.services;

import com.technokratos.dto.request.AccountNamesRequest;
import com.technokratos.dto.request.AccountPasswordRequest;
import com.technokratos.dto.response.AccountExtendedResponse;
import com.technokratos.dto.response.StudentExtendedResponse;
import com.technokratos.models.AccountEntity;
import com.technokratos.models.EducationCategoryEntity;
import com.technokratos.security.details.AccountUserDetails;

import java.util.UUID;

public interface AccountService {

    AccountEntity findAccountById(UUID id);

    AccountEntity getAccountByEmail(String email);

    void confirmAccount(UUID id);

    AccountExtendedResponse updateAccount(AccountUserDetails user, UUID userId, AccountNamesRequest newData);

    AccountExtendedResponse updateAccount(AccountUserDetails user, UUID userId, AccountPasswordRequest newData);

    AccountExtendedResponse getAccount(UUID userId);

    StudentExtendedResponse mapAccountToStudent(AccountEntity account, EducationCategoryEntity category);
}

