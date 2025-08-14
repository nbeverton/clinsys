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
    public DashboardDTO buildSummary() {
        // Total de pacientes
        long totalPatients = patientRepository.count();

        // Consultas de hoje
        LocalDate today = LocalDate.now();
        long appointmentsToday = appointmentRepository.countByDate(today);

        // Consultas da semana
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek   = today.with(DayOfWeek.SUNDAY);
        long appointmentsThisWeek = appointmentRepository.countByDateBetween(startOfWeek, endOfWeek);

        // últimas consultas
        List<Appointment> lastAppointments = appointmentRepository.findTop5ByOrderByDateDesc();

        // Contagem por status
        long completedAppointments = appointmentRepository.countByStatus("COMPLETED");
        long pendingAppointments = appointmentRepository.countByStatus("PENDING");

        // Status de pagamento
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
