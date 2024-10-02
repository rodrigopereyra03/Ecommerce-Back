package com.example.ecommerce.repositories.sql;

import com.example.ecommerce.domain.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IOrderSQLRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(String status);
    List<Order> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);
}
