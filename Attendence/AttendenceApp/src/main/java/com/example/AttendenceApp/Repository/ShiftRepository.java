package com.example.AttendenceApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long>{
    List<Shift> findByEmployee_EmployeeId(Long employeeId);

}
