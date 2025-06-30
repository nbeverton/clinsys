package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
