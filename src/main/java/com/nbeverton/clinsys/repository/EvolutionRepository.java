package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.Evolution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvolutionRepository extends JpaRepository<Evolution, Long> {

    Page<Evolution> findByPatientId(Long patientId, Pageable pageable);

    Page<Evolution> findByPatientIdAndContentContainingIgnoreCase(Long patientId, String text, Pageable pageable);

    List<Evolution> findTop10ByPatientIdOrderByCreatedAtDesc(Long patientId);

}
