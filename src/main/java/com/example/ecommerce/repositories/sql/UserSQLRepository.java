package com.example.ecommerce.repositories.sql;

import com.example.ecommerce.domain.exceptions.UserNotFoundException;
import com.example.ecommerce.domain.models.User;
import com.example.ecommerce.repositories.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserSQLRepository implements IUserRepository {

    private final IUserSQLRepository repository;

    public UserSQLRepository(IUserSQLRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = repository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User not found with id: "+id);
        }
        return userOptional.get();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> findByNameContainingIgnoreCase(String name) {
        return List.of();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
