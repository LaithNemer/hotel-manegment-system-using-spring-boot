package com.example.HotelManagementSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HousekeepingTaskDto {

    private Long id;
    private String description;
    private Date scheduledDate;
    private Long employeeId;
}
