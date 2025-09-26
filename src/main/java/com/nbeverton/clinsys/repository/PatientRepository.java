package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // O que foi implementado agora:
    Page<Patient> findByUser_Id(Long userId, Pageable pageable);
    Page<Patient> findByUser_IdAndNameContainingIgnoreCase(Long userId, String name, Pageable pageable);
    Page<Patient> findByUser_IdAndCpfContaining(Long userId, String cpf, Pageable pageable);
    Optional<Patient> findByIdAndUser_Id(Long id, Long userId);


    // Os que já estavam implementados (REMOVER):

//    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);
//    Page<Patient> findByCpfContaining(String cpf, Pageable pageable);
//    Optional<Patient> findByCpf(String cpf);
}
