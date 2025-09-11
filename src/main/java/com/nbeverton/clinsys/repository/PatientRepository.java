package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // busca por CPF (containing para permitir pesquisa parcial)
    Page<Patient> findByCpfContaining(String cpf, Pageable pageable);

    Optional<Patient> findByCpf(String cpf);
}
