package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.PatientDTO;
import com.nbeverton.clinsys.dto.PatientResponseDTO;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.security.AuthenticatedUserService;
import com.nbeverton.clinsys.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private AuthenticatedUserService auth;

    @Override
    public PatientResponseDTO createPatient(PatientDTO dto) {
        String normalizedCpf = normalizeCpf(dto.getCpf());
        var current = auth.getCurrentUserOrThrow();

        Patient patient = Patient.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .cpf(normalizedCpf)
                .user(current)
                .build();

        return toResponseDTO(repository.save(patient));
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        Long uid = auth.getCurrentUserIdOrThrow();
        Patient p = repository.findByIdAndUser_Id(id, uid)
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou sem permissão"));

        return toResponseDTO(p);
    }

    @Override
    public Page<PatientResponseDTO> getAllPatients(String nameFilter, String cpfFilter, Pageable pageable) {
        Long uid = auth.getCurrentUserIdOrThrow();
        Page<Patient> page;

        if (cpfFilter != null && !cpfFilter.trim().isEmpty()) {
            String normalizedCpf = normalizeCpf(cpfFilter.trim());
            page = repository.findByUser_IdAndCpfContaining(uid, normalizedCpf, pageable);
        } else if (nameFilter != null && !nameFilter.trim().isEmpty()) {
            page = repository.findByUser_IdAndNameContainingIgnoreCase(uid, nameFilter.trim(), pageable);
        } else {
            page = repository.findByUser_Id(uid, pageable);
        }

        return page.map(this::toResponseDTO);
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientDTO dto) {
        Long uid = auth.getCurrentUserIdOrThrow();
        Patient existing = repository.findByIdAndUser_Id(id, uid)
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou sem permissão"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setBirthDate(dto.getBirthDate());
        existing.setGender(dto.getGender());
        existing.setCpf(normalizeCpf(dto.getCpf()));

        return toResponseDTO(repository.save(existing));
    }

    @Override
    public void deletePatient(Long id) {
        Long uid = auth.getCurrentUserIdOrThrow();
        Patient patient = repository.findByIdAndUser_Id(id, uid)
                .orElseThrow(() -> new AccessDeniedException("Paciente não encontrado ou sem permissão!"));
        repository.delete(patient);
    }

    private PatientResponseDTO toResponseDTO(Patient patient) {
        return PatientResponseDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .birthDate(patient.getBirthDate())
                .gender(patient.getGender())
                .cpf(formatCpfForResponse(patient.getCpf()))
                .build();
    }

    // remove qualquer caractere que não seja dígito (normalização antes de salvar)
    private String normalizeCpf(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("\\D", ""); // apenas dígitos
    }

    // opcional: formata para 000.000.000-00 no response (para a UI)
    private String formatCpfForResponse(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-" + cpf.substring(9);
    }
}
