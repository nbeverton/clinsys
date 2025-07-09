package com.nbeverton.clinsys.dto;

import com.nbeverton.clinsys.model.Patient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientResponseDTO extends Patient {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String birthDate;
    private String gender;

}
