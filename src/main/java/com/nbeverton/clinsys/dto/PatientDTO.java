package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientDTO {

    @NotBlank(message = "O nome é obrigatório!")
    private String name;
    @Email(message = "E-mail inválido!")
    private String email;
    @NotBlank(message = "O telefone é obrigatório!")
    private String phone;
    @NotBlank(message = "A data de nascimento é obrigatória!")
    private String birthDate;
    @NotBlank(message = "O gênero é obrigatório!")
    private String gender;

}
