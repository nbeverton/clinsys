package com.nbeverton.clinsys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF para testes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/patients/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/patients").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/patients/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/patients/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
