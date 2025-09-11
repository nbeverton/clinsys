package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatientDTO {

    @NotBlank(message = "O nome é obrigatório!")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail inválido!")
    private String email;

    @NotBlank(message = "O telefone é obrigatório!")
    private String phone;

    @NotBlank(message = "A data de nascimento é obrigatória!")
    private String birthDate;

    @NotBlank(message = "O gênero é obrigatório!")
    private String gender;

    @NotBlank(message = "O CPF é obrigatório!")
    // aceita CPF formato puro (11 dígitos) ou formatado 000.000.000-00
    @Pattern(regexp = "^(\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$",
            message = "CPF inválido. Use 11 dígitos ou 000.000.000-00")
    private String cpf;

}
