package com.nbeverton.clinsys.dto.dashboard;

import java.util.List;

public record DashboardDTO(
    long totalPatients,
    long appointmentsToday,
    long appointmentsThisWeek,
    List<LastAppointmentsDTO> lastAppointments,
    long completedAppointments,
    long pendingAppointments,
    long paidAppointments,
    long unpaidAppointments

) { }
