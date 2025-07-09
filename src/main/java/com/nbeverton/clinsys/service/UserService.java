package com.nbeverton.clinsys.service;

import com.nbeverton.clinsys.dto.UserDTO;
import com.nbeverton.clinsys.dto.UserResponseDTO;
import com.nbeverton.clinsys.model.User;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserDTO dto);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
}
