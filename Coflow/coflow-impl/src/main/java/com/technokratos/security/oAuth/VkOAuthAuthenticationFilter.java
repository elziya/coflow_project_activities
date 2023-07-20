package com.technokratos.security.oAuth;

import lombok.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.technokratos.security.config.SecurityConfig.OAuthSecurityConfiguration.OAUTH_VK_SUCCESS_URL;

@Component
public class VkOAuthAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {

        if (request.getRequestURI().equals(OAUTH_VK_SUCCESS_URL)) {
            String code = request.getParameter("code");
            SecurityContextHolder.getContext().setAuthentication(new VkOAuthAuthentication(code));
        }
        chain.doFilter(request, response);
    }
}
