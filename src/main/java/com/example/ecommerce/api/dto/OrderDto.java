package com.example.ecommerce.api.dto;

import com.example.ecommerce.domain.Enums.OrderStatus;
import com.example.ecommerce.domain.models.Address;
import com.example.ecommerce.domain.models.Product;
import com.example.ecommerce.domain.models.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {

    private Long id;
    private int amount;
    private Address address;
    private List<ProductDto> products;
    private OrderStatus status;
    private LocalDate dateCreated;
    private User user;
}
