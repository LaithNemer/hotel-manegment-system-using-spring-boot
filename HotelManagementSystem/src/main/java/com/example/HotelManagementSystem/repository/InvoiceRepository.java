package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByReservationId(Long reservationId);
    List<Invoice> findByStatus(String status);
}
