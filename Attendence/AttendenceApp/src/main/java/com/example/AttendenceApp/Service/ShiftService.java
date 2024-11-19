package com.example.AttendenceApp.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.Shift;
import com.example.AttendenceApp.Repository.EmployeeRepository;
import com.example.AttendenceApp.Repository.ShiftRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(); 
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId);
    }

    
    public void allocateShiftToEmployee(Long employeeId, String shiftName, LocalTime startTime, LocalTime endTime, LocalDate startDate, LocalDate endDate) {
        // Check if the employee exists
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        // Create a new shift object
        Shift shift = new Shift();
        shift.setEmployee(employee);
        shift.setShiftName(shiftName);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shift.setStartDate(startDate);
        shift.setEndDate(endDate);

        // Save the shift in the database
        shiftRepository.save(shift);
    }

    public List<Shift> getShiftsForEmployee(Long employeeId) {
        return shiftRepository.findByEmployee_EmployeeId(employeeId);
    }
}
