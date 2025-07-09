package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.PatientDTO;
import com.nbeverton.clinsys.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    void deletePatient(Long id);
    List<PatientResponseDTO> getAllPatients();
    PatientResponseDTO getPatientById(Long id);
    PatientResponseDTO createPatient(PatientDTO dto);
    PatientResponseDTO updatePatient(Long id, PatientDTO dto);
}
