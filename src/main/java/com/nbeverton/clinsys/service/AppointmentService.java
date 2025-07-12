package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDTO createAppointment(AppointmentDTO dto);
    AppointmentResponseDTO getAppointmentById(Long id);
    List<AppointmentResponseDTO> getAllAppointments();
    AppointmentResponseDTO updateAppointment(Long id, AppointmentDTO dto);
    void deleteAppointment(Long id);

}
