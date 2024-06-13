package com.example.demo.mapper;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User toModule(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

    public static UserDTO toDTO(User user){
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public static List<User> toModuleUserList(List<UserDTO> userDTOList){
        List<User> list = new ArrayList<>();
        for (UserDTO userDTO : userDTOList) {
            list.add(toModule(userDTO));
        }
        return list;
    }

    public static List<UserDTO> toDTOUserList(List<User> userList) {
        List<UserDTO> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(toDTO(user));
        }
        return dtoList;
    }

}
