package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvolutionDTO {

    @NotBlank
    private String content;

    @NotNull
    private Long patientId;

    private Long authorId;
    private Long appointmentId;

}
