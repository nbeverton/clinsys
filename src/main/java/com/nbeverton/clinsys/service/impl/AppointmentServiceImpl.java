package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;
import com.nbeverton.clinsys.model.Appointment;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.repository.AppointmentRepository;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.repository.UserRepository;
import com.nbeverton.clinsys.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public AppointmentResponseDTO createAppointment(AppointmentDTO dto) {
        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Appointment appointment = Appointment.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .paid(dto.isPaid())
                .patient(patient)
                .user(user)
                .build();

        return toResponseDTO(repository.save(appointment));
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long id) {
        return toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada")));
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public AppointmentResponseDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Patient patient = patientRepo.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setDescription(dto.getDescription());
        appointment.setStatus(dto.getStatus());
        appointment.setPaid(dto.isPaid());
        appointment.setPatient(patient);
        appointment.setUser(user);

        return toResponseDTO(repository.save(appointment));
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

    @Override
    public void deleteAppointment(Long id) {
        Appointment a = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        repository.delete(a);
    }
}
