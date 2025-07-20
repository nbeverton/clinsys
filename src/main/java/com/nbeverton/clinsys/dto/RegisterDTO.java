package com.nbeverton.clinsys.dto;

import com.nbeverton.clinsys.model.Role;
import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Role role;

}
