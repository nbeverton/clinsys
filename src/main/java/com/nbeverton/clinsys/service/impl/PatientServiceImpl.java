package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.PatientDTO;
import com.nbeverton.clinsys.dto.PatientResponseDTO;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public PatientResponseDTO createPatient(PatientDTO dto) {
        String normalizedCpf = normalizeCpf(dto.getCpf());

        Patient patient = Patient.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .cpf(normalizedCpf)
                .build();

        return toResponseDTO(repository.save(patient));
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        return toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
    }

    @Override
    public Page<PatientResponseDTO> getAllPatients(String nameFilter, String cpfFilter, Pageable pageable) {
        Page<Patient> page;

        if (cpfFilter != null && !cpfFilter.trim().isEmpty()) {
            String normalizedCpf = normalizeCpf(cpfFilter.trim());
            page = repository.findByCpfContaining(normalizedCpf, pageable);
        } else if (nameFilter != null && !nameFilter.trim().isEmpty()) {
            page = repository.findByNameContainingIgnoreCase(nameFilter.trim(), pageable);
        } else {
            page = repository.findAll(pageable);
        }

        return page.map(this::toResponseDTO);
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientDTO dto) {
        Patient existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

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
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
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
