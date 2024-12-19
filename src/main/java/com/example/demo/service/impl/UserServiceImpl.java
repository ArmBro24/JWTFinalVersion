package com.example.demo.service.impl;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public UserDTO getUserByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO userDTO = userRepository.findDTOById(id);
        if (userDTO == null) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        return userDTO;
    }


    @Override
    public UserDTO addUser(UserDTO userDTO) {
        log.debug("Adding a user with name: {}", userDTO.getName());

        User savedUser = userRepository.saveAndFlush(UserMapper.toModule(userDTO));
        UserDTO savedUserDTO = UserMapper.toDTO(savedUser);

        log.info("User added with ID: {}", savedUserDTO);
        return savedUserDTO;

    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        log.debug("Updating user with name: {}", userDTO.getName());


        User updatedUser = userRepository.saveAndFlush(UserMapper.toModule(userDTO));
        UserDTO updatedUserDTO = UserMapper.toDTO(updatedUser);


        log.info("User updated with name: {}", userDTO.getName());
        return updatedUserDTO;
    }


    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.toDTOUserList(userRepository.findAll());
    }

    @Override
    public boolean userExists(UserDTO userDTO) {
        final boolean exists = userRepository.existsById(Long.valueOf(userDTO.getName()));

        log.debug("User existence check for name: {} - Exists: {}", userDTO.getName(), exists);
        return exists;
    }

    private void updateUserInfoDTO(User user, UserDTO userDTO) {
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
    }

}
