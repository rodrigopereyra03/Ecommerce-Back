package com.example.ecommerce.services;

import com.example.ecommerce.api.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface IOrderServices {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    String deleteOrder(Long id);

    List<OrderDto> getOrdersByStatus(String status);

    List<OrderDto> getOrdersByDateRange(LocalDate startDate, LocalDate endDate);
}
