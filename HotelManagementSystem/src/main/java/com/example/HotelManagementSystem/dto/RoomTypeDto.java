package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomTypeDto {

    private Long id;
    private String typeName;
    private String description;
    private BigDecimal price;
    private int capacity;
    private int size;
    private String features;
}
