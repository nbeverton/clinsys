package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.UserResponseDTO;
import com.nbeverton.clinsys.service.PatientService;
import com.nbeverton.clinsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final PatientService service;
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> listAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id){
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
