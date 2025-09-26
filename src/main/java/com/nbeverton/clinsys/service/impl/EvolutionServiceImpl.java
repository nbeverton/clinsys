package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.EvolutionDTO;
import com.nbeverton.clinsys.dto.EvolutionResponseDTO;
import com.nbeverton.clinsys.model.Evolution;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.repository.AppointmentRepository;
import com.nbeverton.clinsys.repository.EvolutionRepository;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.security.AuthenticatedUserService;
import com.nbeverton.clinsys.service.EvolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
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
    private AppointmentRepository appointmentRepo;

    @Autowired
    private AuthenticatedUserService auth;


    @Override
    public EvolutionResponseDTO create(EvolutionDTO dto) {
        var current = auth.getCurrentUserOrThrow();

        Patient patient = patientRepo.findByIdAndUser_Id(dto.getPatientId(), current.getId())
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou sem permissão."));

        Evolution e = Evolution.builder()
                .content(dto.getContent())
                .patient(patient)
                .author(current)
                .build();

        if (dto.getAppointmentId() != null) {
            var appt = appointmentRepo.findByIdAndUser_Id(dto.getAppointmentId(), current.getId())
                    .orElseThrow(() -> new AccessDeniedException("Consulta não encontrada ou usuário sem permissão."));
            if (!appt.getPatient().getId().equals(patient.getId())) {
                throw new IllegalArgumentException("A consulta não pertence ao paciente informado.");
            }
            e.setAppointment(appt);
        }

        Evolution saved = repo.save(e);
        return toResponseDTO(saved);
    }

    @Override
    public EvolutionResponseDTO update(Long id, EvolutionDTO dto) {
        var current = auth.getCurrentUserOrThrow();

        Evolution existing = repo.findByIdAndPatient_User_Id(id, current.getId())
                .orElseThrow(() -> new AccessDeniedException("Evolução não encontrada ou usuário sem permissão."));

        existing.setContent(dto.getContent());

        // opcional: vincular/atualizar appointment
        if (dto.getAppointmentId() != null) {
            var appt = appointmentRepo.findByIdAndUser_Id(dto.getAppointmentId(), current.getId())
                    .orElseThrow(() -> new AccessDeniedException("Consulta não encontrada ou usuário sem permissão."));
            existing.setAppointment(appt);
        }

        Evolution updated = repo.save(existing);
        return toResponseDTO(updated);
    }

    @Override
    public void delete(Long id) {
        var current = auth.getCurrentUserOrThrow();

        Evolution e = repo.findByIdAndPatient_User_Id(id, current.getId())
                .orElseThrow(() -> new AccessDeniedException("Evolução não encontrada ou usuário sem permissão."));
        repo.delete(e);
    }

    @Override
    @Transactional(readOnly = true)
    public EvolutionResponseDTO getById(Long id) {
        var current = auth.getCurrentUserOrThrow();

        Evolution e = repo.findByIdAndPatient_User_Id(id, current.getId())
                .orElseThrow(() -> new AccessDeniedException("Evolução não encontrada ou usuário sem permissão."));
        return toResponseDTO(e);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EvolutionResponseDTO> getByPatient(Long patientId, Pageable pageable) {
        var current = auth.getCurrentUserOrThrow();

        patientRepo.findByIdAndUser_Id(patientId, current.getId())
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado."));

        Page<Evolution> page = repo.findByPatient_IdAndPatient_User_Id(patientId, current.getId(), pageable);
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
