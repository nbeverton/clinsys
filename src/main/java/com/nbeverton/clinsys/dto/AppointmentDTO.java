package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {

    @NotNull(message = "O campo data é obrigatório")
    private LocalDate date;

    @NotBlank(message = "O campo hora é obrigatório")
    private String time;

    private String description;

    @NotBlank(message = "O campo status é obrigatório")
    private String status;

    private boolean paid;

    @NotNull(message = "O Id do paciente é obrigatório")
    private Long patientId;

}
