package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationDto {

    private Long id;
    private Long customerId;
    private Long roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
}