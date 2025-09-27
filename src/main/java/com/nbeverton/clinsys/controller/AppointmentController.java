package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.AppointmentDTO;
import com.nbeverton.clinsys.dto.AppointmentResponseDTO;
import com.nbeverton.clinsys.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(service.createAppointment(dto));
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAllAppointments(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAppointmentById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getByPatient(patientId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AppointmentDTO dto) {
        return ResponseEntity.ok(service.updateAppointment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteAppointment(id);
        return ResponseEntity.ok("Consulta excluída com sucesso.");
    }
}
