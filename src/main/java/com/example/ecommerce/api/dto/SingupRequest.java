package com.example.ecommerce.api.dto;

import com.example.ecommerce.domain.models.Address;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SingupRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int documentNumber;
    private int phone;
    private List<Address> address;
    private LocalDateTime dateCreated;

}
