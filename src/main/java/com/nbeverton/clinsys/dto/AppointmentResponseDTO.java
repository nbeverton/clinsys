package com.nbeverton.clinsys.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponseDTO {

    private Long id;
    private String date;
    private String time;
    private String description;
    private String status;
    private boolean paid;

    private String patientName;
    private String userName;

}
