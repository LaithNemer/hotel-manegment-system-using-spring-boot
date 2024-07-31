package com.example.HotelManagementSystem.repository;

import com.example.HotelManagementSystem.entity.HousekeepingTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface HousekeepingTaskRepository extends JpaRepository<HousekeepingTask, Long> {
    List<HousekeepingTask> findByScheduledDate(Date scheduledDate);
    List<HousekeepingTask> findByEmployeeId(Long employeeId);
}
