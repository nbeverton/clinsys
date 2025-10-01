package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentDTO dto);
    AppointmentResponseDTO getAppointmentById(Long id);
    Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable);
    List<AppointmentResponseDTO> getByPatient(Long patientId);
    AppointmentResponseDTO updateAppointment(Long id, AppointmentDTO dto);
    void deleteAppointment(Long id);
    Page<AppointmentResponseDTO> searchAppointments(
            LocalDate startDate,
            LocalDate endDate,
            Boolean paid,
            java.util.List<String> statuses,
            Pageable pageable);
}
