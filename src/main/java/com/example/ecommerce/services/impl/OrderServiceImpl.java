package com.example.ecommerce.services.impl;

import com.example.ecommerce.api.dto.OrderDto;
import com.example.ecommerce.api.mappers.OrderMapper;
import com.example.ecommerce.domain.models.Order;
import com.example.ecommerce.repositories.IOrderRepository;
import com.example.ecommerce.services.IOrderServices;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderServices {

    private final IOrderRepository iOrderRepository;

    public OrderServiceImpl(IOrderRepository iOrderRepository) {
        this.iOrderRepository = iOrderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        return OrderMapper.toOrderDTO(iOrderRepository.save(order));
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = iOrderRepository.findAll();
        return orderList.stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return OrderMapper.toOrderDTO(iOrderRepository.findById(id));
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return null;
    }

    @Override
    public String deleteOrder(Long id) {
        if (iOrderRepository.existsById(id)){
            iOrderRepository.deleteById(id);
            return "The Order has been successfully deleted";
        }else {
            return "The Order not found with id: " + id;
        }

    }

    @Override
    public List<OrderDto> getOrdersByStatus(String status) {
        List<Order> orders = iOrderRepository.findByStatus(status);
        return orders.stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = iOrderRepository.findByDateCreatedBetween(startDate, endDate);
        return orders.stream()
                .map(OrderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }
}
