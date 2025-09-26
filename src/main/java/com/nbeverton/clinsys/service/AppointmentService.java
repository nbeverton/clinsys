package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentDTO dto);
    AppointmentResponseDTO getAppointmentById(Long id);
    Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable);
    AppointmentResponseDTO updateAppointment(Long id, AppointmentDTO dto);
    void deleteAppointment(Long id);

}
