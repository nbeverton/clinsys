package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.DashboardDTO;
import com.nbeverton.clinsys.dto.dashboard.LastAppointmentsDTO;
import com.nbeverton.clinsys.model.Appointment;
import com.nbeverton.clinsys.repository.AppointmentRepository;
import com.nbeverton.clinsys.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    @Transactional
    public DashboardDTO getDashboardData() {
        long totalPatients = patientRepository.count();

        long appointmentsToday = appointmentRepository.countByDate(LocalDate.now());
        long appointmentsThisWeek = appointmentRepository.countByDateBetween(
                LocalDate.now().with(DayOfWeek.MONDAY),
                LocalDate.now().with(DayOfWeek.SUNDAY)
        );

        List<Appointment> lastAppointments = appointmentRepository.findTop5ByOrderByDateDesc();

        long completedAppointments = appointmentRepository.countByStatus("COMPLETED");
        long pendingAppointments = appointmentRepository.countByStatus("PENDING");

        long paidAppointments = appointmentRepository.countByPaidTrue();
        long unpaidAppointments = appointmentRepository.countByPaidFalse();

        List<LastAppointmentsDTO> lastAppointmentsDTOs = lastAppointments.stream()
                .map(appointment -> new LastAppointmentsDTO(
                        appointment.getId(),
                        appointment.getPatient().getName(),
                        appointment.getDate(),
                        appointment.getStatus(),
                        appointment.isPaid()
                ))
                .toList();

        return new DashboardDTO(
                totalPatients,
                appointmentsToday,
                appointmentsThisWeek,
                lastAppointmentsDTOs,
                completedAppointments,
                pendingAppointments,
                paidAppointments,
                unpaidAppointments
        );
    }

}
