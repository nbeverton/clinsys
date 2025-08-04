package com.nbeverton.clinsys.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrevoEmailService {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.sender.name}")
    private String senderName;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendEmail(String toEmail, String subject, String content) {
        HttpPost request;
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            request = new HttpPost("https://api.brevo.com/v3/smtp/email");

            Map<String, Object> emailPayload = new HashMap<>();
            emailPayload.put("sender", Map.of("name", senderName, "email", senderEmail));
            emailPayload.put("to", new Map[]{Map.of("email", toEmail)});
            emailPayload.put("subject", subject);
            emailPayload.put("htmlContent", "<html><body>" + content + "</body></html>");

            String json = objectMapper.writeValueAsString(emailPayload);
            request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
            request.addHeader("api-key", apiKey);
            request.addHeader("accept", "application/json");
            request.addHeader("content-type", "application/json");

            httpClient.execute(request);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar e-mail via Brevo");
        }

    }
}
