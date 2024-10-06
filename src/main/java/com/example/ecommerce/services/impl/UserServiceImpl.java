package com.example.ecommerce.services.impl;

import com.example.ecommerce.api.dto.UserDto;
import com.example.ecommerce.api.mappers.UserMapper;
import com.example.ecommerce.domain.exceptions.UserNotFoundException;
import com.example.ecommerce.domain.models.User;
import com.example.ecommerce.repositories.IUserRepository;
import com.example.ecommerce.services.IUserServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserServices {

    private final IUserRepository repository;

    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.dtoToUser(userDto);
        return UserMapper.userToDto(repository.save(user));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = repository.findAll();
        return users.stream()
                .map(UserMapper::userToDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(()->new UserNotFoundException("User not found with id: " + id));
        return UserMapper.userToDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
            User entity = user.get();
            if (userDto.getFirstName() != null) {
                entity.setFirstName(userDto.getFirstName());
            }
            if (userDto.getLastName() != null) {
                entity.setLastName(userDto.getLastName());
            }
            if (userDto.getEmail() != null) {
                entity.setEmail(userDto.getEmail());
            }
            if (userDto.getPassword() != null) {
                entity.setPassword(userDto.getPassword());
            }
            if (userDto.getDocumentNumber() != 0) {
                entity.setDocumentNumber(userDto.getDocumentNumber());
            }
            if (userDto.getPhone() != 0) {
                entity.setPhone(userDto.getPhone());
            }
            if (userDto.getRol() != null) {
                entity.setRol(userDto.getRol());
            }
            User savedUser = repository.save(entity);
            return UserMapper.userToDto(savedUser);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public String deleteUser(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "User has been successfully deleted";
        } else {
            return "User not found with id: " + id;
        }
    }

    @Override
    public List<UserDto> searchUsers(String firstName, String lastName, String email) {
        return List.of();
    }
}