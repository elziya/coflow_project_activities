package com.technokratos.security.oAuth;

import com.technokratos.security.details.AccountUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class VkOAuthAuthentication implements Authentication {

    private final String code;
    private AccountUserDetails userDetails;
    private boolean isAuthenticated;

    public VkOAuthAuthentication(String code) {
        this.code = code;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = (AccountUserDetails) userDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    public String getCode() {
        return code;
    }
}