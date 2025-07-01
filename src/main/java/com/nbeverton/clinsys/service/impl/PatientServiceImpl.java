package com.nbeverton.clinsys.service.impl;

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
    public Patient createPatient(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    @Override
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    @Override
    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = getPatientById(id);
        existing.setName(updatedPatient.getName());
        existing.setEmail(updatedPatient.getEmail());
        existing.setPhone(updatedPatient.getPhone());
        existing.setBirthDate(updatedPatient.getBirthDate());
        existing.setGender(updatedPatient.getGender());
        return repository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        repository.delete(patient);
    }
}
