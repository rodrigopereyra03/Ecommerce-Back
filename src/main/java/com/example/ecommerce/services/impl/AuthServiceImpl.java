package com.example.ecommerce.services.impl;

import com.example.ecommerce.api.dto.SingupRequest;
import com.example.ecommerce.api.dto.UserDto;
import com.example.ecommerce.domain.Enums.UserRol;
import com.example.ecommerce.domain.models.User;
import com.example.ecommerce.repositories.sql.IUserSQLRepository;
import com.example.ecommerce.services.IAuthServices;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthServices {

    private final IUserSQLRepository userSQLRepository;

    public AuthServiceImpl(IUserSQLRepository userSQLRepository) {
        this.userSQLRepository = userSQLRepository;
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userSQLRepository.findByRol(UserRol.ADMIN);
        if(adminAccount==null){
            User user = new User();
            user.setFirstName("admin");
            user.setEmail("admin@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            user.setRol(UserRol.ADMIN);
            userSQLRepository.save(user);
        }
    }

    @Override
    public UserDto createUser(SingupRequest singupRequest) {
        User user = new User();
        user.setFirstName(singupRequest.getName());
        user.setEmail(singupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
        user.setRol(UserRol.CUSTOMER);
        User created = userSQLRepository.save(user);
        UserDto dto = new UserDto();
        dto.setId(created.getId());
        dto.setFirstName(created.getFirstName());
        dto.setEmail(created.getEmail());
        dto.setRol(created.getRol());
        // UserMapper.userToDto(userSQLRepository.save(UserMapper.dtoToUser(singupRequest)));
        return dto;
    }
}
