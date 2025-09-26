package com.nbeverton.clinsys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private byte[] tokenHash;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime usedAt;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String createdIp;
    private String userAgent;

    @Transient
    public boolean isUsed(){
        return usedAt != null;
    }

    @Transient
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiresAt);
    }

}
