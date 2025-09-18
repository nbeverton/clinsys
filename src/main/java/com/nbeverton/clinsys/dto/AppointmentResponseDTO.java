package com.nbeverton.clinsys.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AppointmentResponseDTO {

    private Long id;
    private java.time.LocalDate date;
    private String time;
    private String description;
    private String status;
    private boolean paid;

    private String patientName;
    private String userName;

    // IDs diretos para facilitar edição no front.
    private Long patientId;
    private Long userId;

}
