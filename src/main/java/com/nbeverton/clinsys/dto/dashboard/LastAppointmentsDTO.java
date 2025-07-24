package com.nbeverton.clinsys.dto.dashboard;

public record LastAppointmentsDTO(
        long id,
        String patientName,
        java.time.LocalDate date,
        String status,
        boolean paid
) { }
