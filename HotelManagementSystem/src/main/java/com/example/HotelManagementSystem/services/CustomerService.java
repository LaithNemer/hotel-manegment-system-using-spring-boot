package com.example.HotelManagementSystem.services;

import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.exception.ResourceNotFoundException;

import java.util.List;

public interface CustomerService {
    CustomerDTO register(CustomerDTO customerDto) throws BadRequestException;
    CustomerDTO login(String email, String password);
    CustomerDTO updateProfile(Long id, CustomerDTO customerDto) throws ResourceNotFoundException;
    void changePassword(Long id, String newPassword) throws ResourceNotFoundException;
    CustomerDTO getCustomerById(Long id) throws ResourceNotFoundException;
    List<CustomerDTO> getAllCustomers();
}
