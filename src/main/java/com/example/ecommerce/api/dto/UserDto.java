package com.example.ecommerce.api.dto;

import com.example.ecommerce.domain.models.Address;
import com.example.ecommerce.domain.models.Order;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dateCreated;
    private int documentNumber;
    private int phone;
    private String rol;
    private List<Order> orders;
    private List<Address> addresses;
}
