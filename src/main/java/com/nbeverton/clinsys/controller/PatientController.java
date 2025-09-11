package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.PatientDTO;
import com.nbeverton.clinsys.dto.PatientResponseDTO;
import com.nbeverton.clinsys.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@RequestBody @Valid PatientDTO dto) {
        return ResponseEntity.ok(service.createPatient(dto));
    }

    @GetMapping
    public ResponseEntity<Page<PatientResponseDTO>> getAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "cpf", required = false) String cpf,
            Pageable pageable) {

        // usa 'name' se fornecido, senão usa 'q' — cpf tem preferência quando informado
        String nameFilter = (name != null && !name.isBlank()) ? name : q;
        Page<PatientResponseDTO> page = service.getAllPatients(nameFilter, cpf, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody PatientDTO dto) {
        return ResponseEntity.ok(service.updatePatient(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.ok("Paciente deletado com sucesso.");
    }

}
