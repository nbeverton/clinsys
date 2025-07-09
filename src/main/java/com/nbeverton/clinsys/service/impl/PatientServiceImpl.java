package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.PatientDTO;
import com.nbeverton.clinsys.dto.PatientResponseDTO;
import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.repository.PatientRepository;
import com.nbeverton.clinsys.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public PatientResponseDTO createPatient(PatientDTO dto) {
        Patient patient = Patient.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birthDate(dto.getBirthDate())
                .gender(dto.getGender())
                .build();

        return toResponseDTO(repository.save(patient));
    }

    @Override
    public PatientResponseDTO getPatientById(Long id) {
        return toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado")));
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
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

        return toResponseDTO(repository.save(existing));
    }

    @Override
    public void deletePatient(Long id) {
        PatientResponseDTO patient = getPatientById(id);
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
                .build();
    }
}
