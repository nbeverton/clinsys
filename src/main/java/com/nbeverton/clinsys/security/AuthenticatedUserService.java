package com.nbeverton.clinsys.security;

import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;

@Component
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public User getCurrentUserOrThrow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equalsIgnoreCase(String.valueOf(auth.getPrincipal()))) {
            throw new AccessDeniedException("Usuário não autenticado.");
        }
        String email = auth.getName();
        return userRepository.findByEmail(email) // TODO: aqui o nosso usuário é email. Mudar no futuro se criar outro tipo de username.
                .orElseThrow(() -> new AccessDeniedException("Usuário não encontrado! Verifique seu email: " + email));
    }

    public Long getCurrentUserIdOrThrow(){
        return getCurrentUserOrThrow().getId();
    }

}
