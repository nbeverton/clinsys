package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByTokenHash(byte[] tokenHash);
}
