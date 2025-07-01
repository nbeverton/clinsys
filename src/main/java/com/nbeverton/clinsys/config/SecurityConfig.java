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
                        // Permissões para /patients
                        .requestMatchers(HttpMethod.GET, "/patients/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/patients").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/patients/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/patients/**").permitAll()

                        // Permissões para /users
                        .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/users/**").permitAll()

                        // Qualquer outro endpoint precisa de autenticação
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
