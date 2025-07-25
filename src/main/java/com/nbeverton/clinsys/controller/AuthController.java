package com.nbeverton.clinsys.controller;

import com.nbeverton.clinsys.dto.AuthRequestDTO;
import com.nbeverton.clinsys.dto.AuthResponseDTO;
import com.nbeverton.clinsys.dto.UserDTO;
import com.nbeverton.clinsys.model.Role;
import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.repository.UserRepository;
import com.nbeverton.clinsys.security.JWTUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;


    // Atualizações feitas aqui.
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(AuthResponseDTO.builder().token(token).build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        var user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.valueOf(dto.getRole().toUpperCase()))
                .build();

        userRepository.save(user);
        var token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(AuthResponseDTO.builder().token(token).build());
    }

}
