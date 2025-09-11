package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.PatientDTO;
import com.nbeverton.clinsys.dto.PatientResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {
    void deletePatient(Long id);
    Page<PatientResponseDTO> getAllPatients(String nameFilter, String cpfFilter, Pageable pageable);
    PatientResponseDTO getPatientById(Long id);
    PatientResponseDTO createPatient(PatientDTO dto);
    PatientResponseDTO updatePatient(Long id, PatientDTO dto);
}
