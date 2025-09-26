package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.model.PasswordResetToken;
import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.repository.PasswordResetTokenRepository;
import com.nbeverton.clinsys.repository.UserRepository;
import com.nbeverton.clinsys.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepo;
    private final PasswordResetTokenRepository tokenRepo;
    private final PasswordEncoder passwordEncoder;

    private final int TOKEN_BYTES = 32;
    private final int EXP_MINUTES = 30;
    private final String APP_URL = "http://localhost:5500/reset.html"; // página do front - Tem que estar de acordo ATENÇÃO!!!

    @Override
    public void initiate(String email, String createdIp, String userAgent) {
        Optional<User> ou = userRepo.findByEmail(email);
        // Resposta é sempre "200 OK" genérica para evitar enumeração de e-mails

        if (ou.isEmpty()) {
            log.info("[forgot] pedido para e-mail inexistente: {}", email);
            return;
        }
        var user = ou.get();

        // gera token aleatório e hash
        String token = generateToken();
        byte[] hash = sha256(token);

        var prt = PasswordResetToken.builder()
                .tokenHash(hash)
                .user(user)
                .expiresAt(LocalDateTime.now().plusMinutes(EXP_MINUTES))
                .createdAt(LocalDateTime.now())
                .createdIp(createdIp)
                .userAgent(userAgent)
                .build();

        tokenRepo.save(prt);

        String link = APP_URL + "?token=" + token;

        // Envie por e-mail (Brevo) — aqui, logamos para dev:
        log.info("[forgot] link de reset para {}: {}", email, link);
        // TODO: integrar com seu serviço de e-mail. Ex.: mailService.sendPasswordReset(email, link);
    }

    @Override
    public void reset(String token, String newPassword) {
        byte[] hash = sha256(token);
        var prt = tokenRepo.findByTokenHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("Token inválido"));
        if (prt.isUsed() || prt.isExpired()) {
            throw new IllegalStateException("Token expirado ou já utilizado");
        }

        User u = prt.getUser();
        u.setPassword(passwordEncoder.encode(newPassword));
        u.setPasswordChangedAt(LocalDateTime.now());
        userRepo.save(u);

        prt.setUsedAt(LocalDateTime.now());
        tokenRepo.save(prt);

        log.info("[reset] senha alterada para userId={}", u.getId());
    }

    private String generateToken() {
        byte[] buf = new byte[TOKEN_BYTES];
        new SecureRandom().nextBytes(buf);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(buf);
    }

    private byte[] sha256(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(s.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
