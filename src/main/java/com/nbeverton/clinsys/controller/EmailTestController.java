package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.service.BrevoEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailTestController {

    private final BrevoEmailService emailService;

    @PostMapping("/test")
    public String senTestEmail(@RequestParam String to) {
        emailService.sendEmail(to, "Teste ClinSys,", "Esse é um e-mail de teste enviado via Brevo. Se você está recebendo, é porque tudo funciona perfeitamente :).");
        return "E-mail enviado com sucesso para: " + to;
    }

}
