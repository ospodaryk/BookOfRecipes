package com.project.recipes.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // todo: check https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

    @Autowired
    UserDetailsService userServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling(eh -> eh
                        .authenticationEntryPoint(restAuthenticationEntryPoint())
                )
                .httpBasic(hb -> hb
                        .authenticationEntryPoint(restAuthenticationEntryPoint()) // Handles auth error
                )
                .csrf().disable()
                .headers(h -> h
                        .frameOptions() // for Postman, the H2 console
                        .disable()
                )
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no session
                )
                .authorizeHttpRequests(a -> a
                        .anyRequest().permitAll() // todo
                );
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            log.warn("Authentication for '{} {}' failed with error: {}",
                    request.getMethod(), request.getRequestURL(), authException.getMessage());
            response.sendError(
                    UNAUTHORIZED.value(), authException.getMessage());
        };
    }
}
