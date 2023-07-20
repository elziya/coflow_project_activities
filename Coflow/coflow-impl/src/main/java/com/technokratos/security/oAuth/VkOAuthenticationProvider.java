package com.technokratos.security.oAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class VkOAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Autowired
    public VkOAuthenticationProvider(@Qualifier("userDetailsVkOAuthService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VkOAuthAuthentication vkAuthentication = (VkOAuthAuthentication) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(vkAuthentication.getCode());
        vkAuthentication.setUserDetails(userDetails);
        vkAuthentication.setAuthenticated(true);
        return vkAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return VkOAuthAuthentication.class.equals(authentication);
    }
}
