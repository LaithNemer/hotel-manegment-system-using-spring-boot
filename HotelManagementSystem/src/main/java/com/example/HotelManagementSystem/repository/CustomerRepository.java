package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
    Customer findByEmail(String email);
    Customer findByUsername(String username);
    List<Customer> findByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}