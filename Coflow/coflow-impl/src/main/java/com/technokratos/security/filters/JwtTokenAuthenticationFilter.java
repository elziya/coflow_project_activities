package com.technokratos.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.dto.enums.State;
import com.technokratos.dto.request.UserRequest;
import com.technokratos.exceptions.CoflowNotConfirmedAccountException;
import com.technokratos.exceptions.CoflowUnauthorizedException;
import com.technokratos.models.AccountEntity;
import com.technokratos.security.details.AccountUserDetails;
import com.technokratos.services.AccountService;
import com.technokratos.services.JwtTokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    private final JwtTokenService jwtTokenService;

    private final AccountService accountService;

    public JwtTokenAuthenticationFilter(JwtTokenService jwtTokenService, AuthenticationManager manager, ObjectMapper objectMapper,
                                        AccountService accountService) {
        super.setAuthenticationManager(manager);
        this.jwtTokenService = jwtTokenService;
        this.objectMapper = objectMapper;
        this.accountService = accountService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserRequest userRequest = null;
        try {
            userRequest = objectMapper.readValue(request.getReader(), UserRequest.class);
        } catch (Exception e) {
            throw new CoflowUnauthorizedException("Failed to attempt authentication");
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword());

        if (!State.CONFIRMED.equals(accountService.getAccountByEmail(userRequest.getEmail()).getState())) {
            throw new CoflowNotConfirmedAccountException("Can't authenticate user with not confirmed email");
        }

        return super.getAuthenticationManager().authenticate(token);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        AccountUserDetails userDetails = (AccountUserDetails) authResult.getPrincipal();
        AccountEntity account = userDetails.getAccount();

        objectMapper.writeValue(response.getWriter(), jwtTokenService.getTokenCouple(account));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException{
        throw failed;
    }
}

