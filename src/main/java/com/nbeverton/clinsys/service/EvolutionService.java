package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.EvolutionDTO;
import com.nbeverton.clinsys.dto.EvolutionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EvolutionService {

    EvolutionResponseDTO create(EvolutionDTO dto);
    EvolutionResponseDTO update(Long id, EvolutionDTO dto);
    void delete(Long id);
    EvolutionResponseDTO getById(Long id);
    Page<EvolutionResponseDTO> getByPatient(Long patientId, Pageable pageable);

}
