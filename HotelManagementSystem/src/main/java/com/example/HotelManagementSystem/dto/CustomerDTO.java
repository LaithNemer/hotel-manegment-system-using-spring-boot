package com.example.HotelManagementSystem.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
