package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Evolution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvolutionRepository extends JpaRepository<Evolution, Long> {

    // métodos novos:
    Page<Evolution> findByPatient_IdAndPatient_User_Id(Long patientId, Long userId, Pageable pageable);
    Optional<Evolution> findByIdAndPatient_User_Id(Long evolutionId, Long userId);

    // métodos já existentes (Não estão sendo usados - REMOVER):
//    Page<Evolution> findByPatientId(Long patientId, Pageable pageable);
//    Page<Evolution> findByPatientIdAndContentContainingIgnoreCase(Long patientId, String text, Pageable pageable);
//    List<Evolution> findTop10ByPatientIdOrderByCreatedAtDesc(Long patientId);

}
