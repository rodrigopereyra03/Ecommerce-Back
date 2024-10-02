package com.example.ecommerce.repositories.sql;

import com.example.ecommerce.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSQLRepository extends JpaRepository<User, Long> {
}
