package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "O campo nome é obrigatório!")
    private String name;

    @NotBlank(message = "O campo e-mail é obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório!")
    private String password;

    @NotBlank(message = "O campo perfil é obrigatório!")
    private String role;

}
