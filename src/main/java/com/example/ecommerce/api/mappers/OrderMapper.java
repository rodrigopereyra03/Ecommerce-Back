package com.example.ecommerce.api.mappers;

import com.example.ecommerce.api.dto.OrderDto;
import com.example.ecommerce.domain.models.Order;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {

    public OrderDto toOrderDTO(Order order) {
        OrderDto orderDTO = new OrderDto();
        orderDTO.setId(order.getId());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setProducts(order.getProducts().stream()
                .map(ProductMapper::productToDto)
                .collect(Collectors.toList()));
        orderDTO.setStatus(order.getStatus());
        orderDTO.setDateCreated(order.getDateCreated());
        orderDTO.setUser(order.getUser());

        return orderDTO;
    }

    public Order toOrder(OrderDto orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setAmount(orderDTO.getAmount());
        order.setAddress(orderDTO.getAddress());
        order.setProducts(orderDTO.getProducts().stream()
                .map(ProductMapper::dtoToProduct)
                .collect(Collectors.toList()));
        order.setStatus(orderDTO.getStatus());
        order.setDateCreated(orderDTO.getDateCreated());
        order.setUser(orderDTO.getUser());

        return order;
    }
}
