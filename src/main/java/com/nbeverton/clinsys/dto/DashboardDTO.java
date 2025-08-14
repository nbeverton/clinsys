package com.nbeverton.clinsys.dto;

import com.nbeverton.clinsys.dto.dashboard.LastAppointmentsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardDTO {

    private long totalPatients;
    private long appointmentsToday;
    private long appointmentsThisWeek;
    private List<LastAppointmentsDTO> lastAppointments;
    private long completedAppointments;
    private long pendingAppointments;
    private long paidAppointments;
    private long unpaidAppointments;

}
