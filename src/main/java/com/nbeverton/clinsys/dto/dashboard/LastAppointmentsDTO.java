package com.nbeverton.clinsys.dto.dashboard;

import java.time.LocalDateTime;

public record LastAppointmentsDTO(
        long id,
        String patientName,
        LocalDateTime date,
        String status,
        boolean paid
) { }
