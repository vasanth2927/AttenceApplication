package com.example.AttendenceApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AttendenceApp.Entity.EmployeeAttendance;
import com.example.AttendenceApp.Repository.EmployeeAttendanceRepository;

@Service
public class AttendanceHistoryService {

    @Autowired
    private EmployeeAttendanceRepository employeeAttendanceRepository;

    public List<EmployeeAttendance> getAttendanceHistoryByEmployeeId(Long employeeId) {
        return employeeAttendanceRepository.findByEmployee_EmployeeIdOrderByAttendanceDateAsc(employeeId);
        
        
        
    }
}
