package com.nbeverton.clinsys.service.impl;

import com.nbeverton.clinsys.dto.UserDTO;
import com.nbeverton.clinsys.dto.UserResponseDTO;
import com.nbeverton.clinsys.model.User;
import com.nbeverton.clinsys.repository.UserRepository;
import com.nbeverton.clinsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserResponseDTO createUser(UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();

        return toResponseDTO(repository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return toResponseDTO(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return toResponseDTO(repository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        repository.delete(user);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
