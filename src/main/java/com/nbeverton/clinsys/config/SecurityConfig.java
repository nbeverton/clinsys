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
                .csrf(csrf -> csrf.disable()) // Desativa CSRF só para facilitar testes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/patients").permitAll() // libera o POST
                        .requestMatchers(HttpMethod.GET, "/patients/**").permitAll()
                        .anyRequest().authenticated()
                );
                 // Ativa o Basic Auth .httpBasic();

        return http.build();
    }

}
