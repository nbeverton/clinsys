package com.nbeverton.clinsys.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String birthDate;
    private String gender;
    private String cpf;

}
