package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.EvolutionDTO;
import com.nbeverton.clinsys.dto.EvolutionResponseDTO;
import com.nbeverton.clinsys.model.Appointment;
import com.nbeverton.clinsys.model.Evolution;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.repository.AppointmentRepository;
import com.nbeverton.clinsys.repository.EvolutionRepository;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.repository.UserRepository;
import com.nbeverton.clinsys.service.EvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class EvolutionServiceImpl implements EvolutionService {

    @Autowired
    private EvolutionRepository repo;

    @Autowired
    private PatientRepository patientRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AppointmentRepository appointmentRepo;


    @Override
    public EvolutionResponseDTO create(EvolutionDTO dto) {
        Patient patient = patientRepo.findById(dto.getPatientId())
            .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Evolution e = Evolution.builder()
                .content(dto.getContent())
                .patient(patient)
                .build();

        if (dto.getAuthorId() != null) {
            User u = userRepo.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            e.setAuthor(u);
        }

        if (dto.getAppointmentId() != null) {
            Appointment a = appointmentRepo.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
            e.setAppointment(a);
        }

        Evolution saved = repo.save(e);
        return toResponseDTO(saved);
    }

    @Override
    public EvolutionResponseDTO update(Long id, EvolutionDTO dto) {
        Evolution existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evolução não encontrada"));

        existing.setContent(dto.getContent());
        Evolution updated = repo.save(existing);
        return toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public EvolutionResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public Page<EvolutionResponseDTO> getByPatient(Long patientId, Pageable pageable) {
        return null;
    }

    private EvolutionResponseDTO toResponseDTO(Evolution e) {
        return EvolutionResponseDTO.builder()
                .id(e.getId())
                .content(e.getContent())
                .patientId(e.getPatient().getId())
                .authorId(e.getAuthor() != null ? e.getAuthor().getId() : null)
                .authorName(e.getAuthor() != null ? e.getAuthor().getName() : null)
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .appointmentId(e.getAppointment() != null ? e.getAppointment().getId() : null)
                .build();
    }
}
