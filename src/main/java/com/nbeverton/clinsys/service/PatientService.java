package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.model.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatientById(Long Id);
    List<Patient> getAllPatients();
    Patient updatePatient(Long id, Patient updatedPatient);
    void deletePatient(Long id);
}
