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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

        // atualiza conteúdo
        existing.setContent(dto.getContent());

        // opcional: atualizar autor (se fornecido)
        if (dto.getAuthorId() != null) {
            User u = userRepo.findById(dto.getAuthorId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            existing.setAuthor(u);
        }

        // opcional: vincular/atualizar appointment
        if (dto.getAppointmentId() != null) {
            Appointment a = appointmentRepo.findById(dto.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
            existing.setAppointment(a);
        }

        Evolution updated = repo.save(existing);
        return toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Evolution e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evolução não encontrada!"));
        repo.delete(e);
    }

    @Override
    @Transactional(readOnly = true)
    public EvolutionResponseDTO getById(Long id) {
        Evolution e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Evolução não encontrada!"));
        return toResponseDTO(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EvolutionResponseDTO> getByPatient(Long patientId, Pageable pageable) {
        Page<Evolution> page = repo.findByPatientId(patientId, pageable);
        return page.map(this::toResponseDTO);
    }

    private EvolutionResponseDTO toResponseDTO(Evolution e) {
        return EvolutionResponseDTO.builder()
                .id(e.getId())
                .content(e.getContent())
                .patientId(e.getPatient() != null ? e.getPatient().getId() : null)
                .authorId(e.getAuthor() != null ? e.getAuthor().getId() : null)
                .authorName(e.getAuthor() != null ? e.getAuthor().getName() : null)
                .createdAt(e.getCreatedAt())
                .updatedAt(e.getUpdatedAt())
                .appointmentId(e.getAppointment() != null ? e.getAppointment().getId() : null)
                .build();
    }
}
