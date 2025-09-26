package com.nbeverton.clinsys.security;

import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String username = jwtUtil.extractUsername(jwt);

        // só segue se ainda não há autenticação no contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.isTokenValid(jwt)) {
                // invalidação de token emitido antes da troca de senha
                Date iat = jwtUtil.extractIssuedAt(jwt);
                if (userDetails instanceof User u) {
                    var changed = u.getPasswordChangedAt();
                    if (changed != null) {
                        var changedInstant = changed.atZone(ZoneId.systemDefault()).toInstant();
                        if (iat == null || iat.toInstant().isBefore(changedInstant)) {
                            // token antigo -> não autentica
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                }

                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
