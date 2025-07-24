package com.nbeverton.clinsys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDTO {

    @NotBlank(message = "O campo data é obrigatório")
    private LocalDate date;

    @NotBlank(message = "O campo hora é obrigatório")
    private String time;

    @NotBlank(message = "O campo descrição é obrigatório")
    private String description;

    @NotBlank(message = "O campo status é obrigatório")
    private String status;

    @NotNull(message = "O status de pagamento é obrigatório")
    private boolean paid;

    @NotNull(message = "O Id do paciente é obrigatório")
    private Long patientId;

    @NotNull(message = "O Id do usuário é obrigatório")
    private Long userId;

}
