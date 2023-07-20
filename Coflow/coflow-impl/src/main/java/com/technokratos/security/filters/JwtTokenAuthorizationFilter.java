package com.technokratos.security.filters;

import com.technokratos.exceptions.CoflowAuthenticationHeaderException;
import com.technokratos.providers.JwtTokenProvider;
import com.technokratos.util.HttpResponseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.technokratos.consts.CoflowConstants.BEARER;
import static com.technokratos.security.config.SecurityConfig.JwtSecurityConfiguration.LOGIN_FILTER_PROCESSES_URL;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(LOGIN_FILTER_PROCESSES_URL)) {
            filterChain.doFilter(request, response);
        } else {

            try {
                String tokenHeader = request.getHeader(AUTHORIZATION);

                if (tokenHeader == null) {
                    filterChain.doFilter(request, response);

                } else if (tokenHeader.startsWith(BEARER.concat(StringUtils.SPACE))) {
                    String token = tokenHeader.substring(BEARER.concat(StringUtils.SPACE).length());

                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    jwtTokenProvider.getRolesFromAccessToken(token, true).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                    });

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetailsService
                                    .loadUserByUsername(jwtTokenProvider.getEmailFromAccessToken(token)),
                                    token, authorities);

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request, response);

                } else {
                    throw new CoflowAuthenticationHeaderException("Invalid authentication scheme found in Authorization header");
                }
            } catch (Exception exception){
                SecurityContextHolder.clearContext();
                HttpResponseUtil.putExceptionInResponse(request, response, exception, HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

}
