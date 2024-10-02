package com.example.ecommerce.services;

import com.example.ecommerce.api.dto.UserDto;

import java.util.List;

public interface IUserServices {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    String deleteUser(Long id);

    List<UserDto> searchUsers(String firstName, String lastName, String email);
}