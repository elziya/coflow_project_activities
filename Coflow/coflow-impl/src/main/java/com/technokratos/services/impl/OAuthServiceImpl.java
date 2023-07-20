package com.technokratos.services.impl;

import com.technokratos.dto.enums.Role;
import com.technokratos.dto.oAuth.VkAccessTokenDto;
import com.technokratos.dto.oAuth.VkAccountDto;
import com.technokratos.dto.oAuth.VkRequest;
import com.technokratos.dto.response.TokenCoupleResponse;
import com.technokratos.exceptions.CoflowOAuthException;
import com.technokratos.models.AccountEntity;
import com.technokratos.repositories.AccountRepository;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.AccountRoleService;
import com.technokratos.services.JwtTokenService;
import com.technokratos.services.OAuthService;
import com.technokratos.util.oAuth.VkOAuthUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OAuthServiceImpl implements OAuthService {

    private final AccountRepository accountRepository;

    private final VkOAuthUtility vkUtility;

    private final JwtTokenService jwtTokenService;
    private final AccountRoleService accountRoleService;

    @Override
    public AccountEntity authCode(String code) {
        VkAccessTokenDto token = vkUtility.getAccessToken(code);

        if (token.getEmail() != null) {
            Optional<AccountEntity> accountOptional = accountRepository.findByEmail(token.getEmail());

            if (accountOptional.isPresent()) {
                return accountOptional.get();
            }

            VkAccountDto vkUserDto = vkUtility.getAccount(token);
            AccountEntity account = AccountEntity.builder()
                    .firstName(vkUserDto.getFirstName())
                    .lastName(vkUserDto.getLastName())
                    .email(token.getEmail())
                    .build();

            AccountEntity accountEntity = accountRepository.save(account);

            accountRoleService.setSiteAccountRole(accountEntity, Role.USER);

            return accountRepository.getById(accountEntity.getId());
        }

        throw new CoflowOAuthException("Не удалось войти через VK");
    }

    @Override
    public TokenCoupleResponse getTokenCouple() {
        AccountUserDetails accountUserDetails = (AccountUserDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return jwtTokenService.getTokenCouple(accountUserDetails.getAccount());
    }

    @Override
    public VkRequest getVkRequest() {
        return VkRequest.builder()
                .request(vkUtility.getAuthorizeRequest())
                .build();
    }
}
