package com.example.ecommerce.repositories;

import com.example.ecommerce.domain.models.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderRepository {

    Order save(Order order);

    Order findById(Long id);

    List<Order> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    List<Order> findByStatus(String status);

    List<Order> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);
}
