package com.example.demo.service;

import com.example.demo.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    void eraseUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    boolean userExists(final UserDTO userDTO);

}
