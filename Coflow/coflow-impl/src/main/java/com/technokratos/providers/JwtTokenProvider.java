package com.technokratos.providers;

import com.technokratos.models.AccountEntity;

import java.util.Date;
import java.util.List;

public interface JwtTokenProvider {

    String generateAccessToken(AccountEntity account);

    Date getExpirationDateFromAccessToken(String accessToken);

    List<String> getRolesFromAccessToken(String token, boolean inFilter);

    String getSubjectFromAccessToken(String token);

    String getEmailFromAccessToken(String token);
}
