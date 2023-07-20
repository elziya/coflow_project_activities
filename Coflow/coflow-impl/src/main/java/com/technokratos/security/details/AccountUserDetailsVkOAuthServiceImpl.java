package com.technokratos.security.details;

import com.technokratos.services.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsVkOAuthService")
public class AccountUserDetailsVkOAuthServiceImpl implements UserDetailsService {

    private final OAuthService oAuthService;

    @Autowired
    public AccountUserDetailsVkOAuthServiceImpl(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        return new AccountUserDetails(oAuthService.authCode(code));
    }
}
