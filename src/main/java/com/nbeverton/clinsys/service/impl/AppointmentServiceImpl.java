package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;
import com.nbeverton.clinsys.model.Appointment;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.repository.AppointmentRepository;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.security.AuthenticatedUserService;
import com.nbeverton.clinsys.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private AuthenticatedUserService auth;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentDTO dto) {
        var current = auth.getCurrentUserOrThrow();

        Patient patient = patientRepo.findByIdAndUser_Id(dto.getPatientId(), current.getId())
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou sem permissão"));

        Appointment appointment = Appointment.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .paid(dto.isPaid())
                .patient(patient)
                .user(current)      // Aqui eu faço com que o usuário seja o autor da consulta.
                .build();

        return toResponseDTO(repository.save(appointment));
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        Long uid = auth.getCurrentUserIdOrThrow();

        Appointment a = repository.findByIdAndUser_Id(id, uid)
                .orElseThrow(() -> new AccessDeniedException("Consulta não encontrada ou sem permissão!"));
        return toResponseDTO(a);
    }


    public Page<AppointmentResponseDTO> getAllAppointments(Pageable pageable) {
        Long uid = auth.getCurrentUserIdOrThrow();
        return repository.findByUser_Id(uid, pageable).map(this::toResponseDTO);
    }

    @Override
    public List<AppointmentResponseDTO> getByPatient(Long patientId){
        var current = auth.getCurrentUserOrThrow();

        patientRepo.findByIdAndUser_Id(patientId, current.getId())
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou usuário sem permissão"));

        var list = repository.findByUser_IdAndPatient_Id(current.getId(), patientId);
        return list.stream().map(this::toResponseDTO).toList();
    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentDTO dto) {
        var current = auth.getCurrentUserOrThrow();

        Appointment appointment = repository.findByIdAndUser_Id(id, current.getId())
                .orElseThrow(() -> new AccessDeniedException("Consulta não encontrada ou sem permissão."));

        Patient patient = patientRepo.findByIdAndUser_Id(dto.getPatientId(), current.getId())
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou sem permissão."));

        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setDescription(dto.getDescription());
        appointment.setStatus(dto.getStatus());
        appointment.setPaid(dto.isPaid());
        appointment.setPatient(patient);
        appointment.setUser(current);

        return toResponseDTO(repository.save(appointment));
    }

    @Override
    public void deleteAppointment(Long id) {
        Long uid = auth.getCurrentUserIdOrThrow();

        Appointment a = repository.findByIdAndUser_Id(id, uid)
                .orElseThrow(() -> new AccessDeniedException("Consulta não encontrada ou sem permissão."));
        repository.delete(a);
    }

    private AppointmentResponseDTO toResponseDTO(Appointment a) {
        // proteger contra nulls por segurança
        Long patientId = a.getPatient() != null ? a.getPatient().getId() : null;
        String patientName = a.getPatient() != null ? a.getPatient().getName() : null;

        Long userId = a.getUser() != null ? a.getUser().getId() : null;
        String userName = a.getUser() != null ? a.getUser().getName() : null;

        return AppointmentResponseDTO.builder()
                .id(a.getId())
                .date(a.getDate())
                .time(a.getTime())
                .description(a.getDescription())
                .status(a.getStatus())
                .paid(a.isPaid())
                .patientName(patientName)
                .userName(userName)
                .patientId(patientId)
                .userId(userId)
                .build();
    }
}
