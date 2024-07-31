package com.example.HotelManagementSystem.services.impl;

import com.example.HotelManagementSystem.dto.CustomerDTO;
import com.example.HotelManagementSystem.entity.Customer;
import com.example.HotelManagementSystem.exception.BadRequestException;
import com.example.HotelManagementSystem.exception.ResourceNotFoundException;
import com.example.HotelManagementSystem.repository.CustomerRepository;
import com.example.HotelManagementSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CustomerDTO register(CustomerDTO customerDto) throws BadRequestException {
        if (customerRepository.existsByEmail(customerDto.getEmail())) {
            throw new BadRequestException("Customer", "email");
        }
        Customer customer = new Customer();
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customerRepository.save(customer);
        customerDto.setId(customer.getId());
        return customerDto;
    }

    @Override
    public CustomerDTO login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null || !passwordEncoder.matches(password, customer.getPassword())) {
            throw new BadRequestException("Customer", "email or password");
        }
        return convertEntityToDTO(customer);
    }

    @Override
    public CustomerDTO updateProfile(Long id, CustomerDTO customerDto) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "id", id));
        customer.setUsername(customerDto.getUsername());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customerRepository.save(customer);
        return convertEntityToDTO(customer);
    }

    @Override
    public void changePassword(Long id, String newPassword) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "id", id));
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "id", id));
        return convertEntityToDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    private CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());
        return customerDTO;
    }
}