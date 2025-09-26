package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank
    private String token;

    @NotBlank
    @Size(min = 8, message = "A nova senha deve ter ao menos 8 caracteres.")
    private String newPassword;

}
