package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentDTO dto);
    AppointmentResponseDTO getAppointmentById(Long id);
    Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable);
    List<AppointmentResponseDTO> getByPatient(Long patientId);
    AppointmentResponseDTO updateAppointment(Long id, AppointmentDTO dto);
    void deleteAppointment(Long id);

}
