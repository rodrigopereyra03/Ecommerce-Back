package com.example.ecommerce.repositories;

import com.example.ecommerce.domain.models.User;
import java.util.List;

public interface IUserRepository {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    List<User> findByNameContainingIgnoreCase(String name);

    boolean existsById(Long id);

}