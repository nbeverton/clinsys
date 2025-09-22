package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.EvolutionDTO;
import com.nbeverton.clinsys.dto.EvolutionResponseDTO;
import com.nbeverton.clinsys.service.EvolutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evolutions")
public class EvolutionController {

    @Autowired
    private EvolutionService service;

    @PostMapping
    public ResponseEntity<EvolutionResponseDTO> create(@Valid @RequestBody EvolutionDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvolutionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<Page<EvolutionResponseDTO>> getByPatient(
            @PathVariable Long patientId, Pageable pageable) {
        return ResponseEntity.ok(service.getByPatient(patientId, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvolutionResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EvolutionDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
