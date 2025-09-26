package com.nbeverton.clinsys.service;

public interface PasswordResetService {

    void initiate(String email, String createdIp, String userAgent);
    void reset(String token, String newPassword);
}
