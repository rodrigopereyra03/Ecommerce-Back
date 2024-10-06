package com.example.ecommerce.services;

import com.example.ecommerce.api.dto.OrderDto;
import com.example.ecommerce.domain.Enums.OrderStatus;
import com.example.ecommerce.domain.models.User;

import java.time.LocalDate;
import java.util.List;

public interface IOrderServices {
    OrderDto createOrder(OrderDto orderDto, String userEmail);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    String deleteOrder(Long id);

    List<OrderDto> getOrdersByStatus(OrderStatus status);

    List<OrderDto> getOrdersByDateRange(LocalDate startDate, LocalDate endDate);

    List<OrderDto> getOrdersByUserEmail(String email);
    OrderDto updateOrderStatus(Long id, OrderStatus status);
}
