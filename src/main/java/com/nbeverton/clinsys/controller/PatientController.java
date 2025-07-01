package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.model.Patient;
import com.nbeverton.clinsys.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient patient) {
        return ResponseEntity.ok(service.createPatient(patient));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll() {
        return ResponseEntity.ok(service.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        return ResponseEntity.ok(service.updatePatient(id, updatedPatient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.ok("Paciente deletado com sucesso.");
    }

}
