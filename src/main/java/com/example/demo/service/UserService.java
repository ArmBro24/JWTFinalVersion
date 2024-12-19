package com.example.demo.service;

import com.example.demo.DTO.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserByName(String name);

    UserDTO getUserById(Long id);


    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUserById(Long id);
    List<UserDTO> getAllUsers();
    boolean userExists(final UserDTO userDTO);

}
