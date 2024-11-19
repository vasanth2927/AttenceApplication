package com.example.AttendenceApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.Payroll;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    Payroll findByEmployee_EmployeeId(Long employeeId); // Use underscore to denote the relationship

}
