package com.technokratos.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technokratos.providers.JwtTokenProvider;
import com.technokratos.security.filters.FilterExceptionHandler;
import com.technokratos.security.filters.JwtTokenAuthenticationFilter;
import com.technokratos.security.filters.JwtTokenAuthorizationFilter;
import com.technokratos.security.oAuth.VkOAuthAuthenticationFilter;
import com.technokratos.security.oAuth.VkOAuthenticationProvider;
import com.technokratos.services.AccountService;
import com.technokratos.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String API = "/api/v1";

    @Order(2)
    @Configuration
    @RequiredArgsConstructor
    public static class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

        public static final String LOGIN_FILTER_PROCESSES_URL = API + "/login";

        public static final String SIGN_UP_PROCESSES_URL = API + "/sign-up";

        private final JwtTokenService jwtTokenService;

        private final JwtTokenProvider jwtTokenProvider;

        private final PasswordEncoder passwordEncoder;

        private final UserDetailsService accountUserDetailsService;

        private final ObjectMapper objectMapper;

        private final AccountService accountService;

        private final FilterExceptionHandler filterExceptionHandler;

        private static final String[] PERMIT_ALL = {
                LOGIN_FILTER_PROCESSES_URL,
                SIGN_UP_PROCESSES_URL,
                API + "/confirm",
                API + "/confirm/confirm-code",
                API + "/token/refresh",
                API + "/search/**"
        };

        private static final String[] IGNORE = {
                "/account-swagger/api-docs",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/webjars/**",
                "/swagger-ui/**"
        };

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(accountUserDetailsService).passwordEncoder(passwordEncoder);
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers(IGNORE);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            JwtTokenAuthenticationFilter authenticationFilter =
                    new JwtTokenAuthenticationFilter(jwtTokenService, authenticationManagerBean(), objectMapper, accountService);
            authenticationFilter.setFilterProcessesUrl(LOGIN_FILTER_PROCESSES_URL);

            JwtTokenAuthorizationFilter authorizationFilter =
                    new JwtTokenAuthorizationFilter(jwtTokenProvider, accountUserDetailsService);

            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .logout().disable();

            http.addFilter(authenticationFilter);
            http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
            http.addFilterBefore(filterExceptionHandler, JwtTokenAuthenticationFilter.class);

            http.authorizeRequests()
                    .antMatchers(PERMIT_ALL).permitAll()
                    .anyRequest().authenticated();
        }
    }

    @Order(1)
    @RequiredArgsConstructor
    @Configuration
    public class OAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {

        public static final String OAUTH_VK_URL = API + "/auth/vk";
        public static final String OAUTH_VK_SUCCESS_URL = API + "/auth/vk/success";

        private final VkOAuthAuthenticationFilter authenticationFilter;

        private final VkOAuthenticationProvider authenticationProvider;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher(API + "/auth/**")
                    .authorizeRequests()
                    .antMatchers(OAUTH_VK_URL).permitAll()
                    .antMatchers(OAUTH_VK_SUCCESS_URL).authenticated()
                    .and()
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider);
        }

    }
}
