package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.ForgotPasswordRequest;
import com.nbeverton.clinsys.dto.ResetPasswordRequest;
import com.nbeverton.clinsys.service.PasswordResetService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordResetService passwordResetService;

    @PostMapping("/forgot")
    public ResponseEntity<?> forgot(@Valid @RequestBody ForgotPasswordRequest req, HttpServletRequest http){
        String ip = getClientIp(http);
        String ua = http.getHeader("User-Agent");
        passwordResetService.initiate(req.getEmail(), ip, ua);
        return ResponseEntity.ok().body(
                new java.util.HashMap<String, Object>() {{
                    put("message", "Caso exista uma conta com este e-mail, você receberá as instruções.");
                }});
    }

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@Valid @RequestBody ResetPasswordRequest req){
        passwordResetService.reset(req.getToken(), req.getNewPassword());
        return ResponseEntity.ok().body(
                new java.util.HashMap<String, Object>() {{
                    put("message", "Senha alterada com sucesso.");
                }});
    }

    private String getClientIp(HttpServletRequest request) {
        String xf = request.getHeader("X-Forwarded-For");
        return xf != null ? xf.split(",")[0] : request.getRemoteAddr();
    }

}
