package com.example.AttendenceApp.Service;

import com.example.AttendenceApp.Entity.Payroll;
import com.example.AttendenceApp.Entity.EmployeeAttendance;
import com.example.AttendenceApp.Entity.SalaryStructure;
import com.example.AttendenceApp.Repository.PayrollRepository;
import com.example.AttendenceApp.Repository.EmployeeAttendanceRepository;
import com.example.AttendenceApp.Repository.SalaryStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeAttendanceRepository attendanceRepository;

    @Autowired
    private SalaryStructureRepository salaryStructureRepository;

    public Payroll calculatePayroll(Long employeeId) {
        // Fetch attendance for the employee
        List<EmployeeAttendance> attendanceList = attendanceRepository.findByEmployee_EmployeeId(employeeId);
        
        // Fetch salary structure for the employee
        SalaryStructure salaryStructure = salaryStructureRepository.findByEmployee_EmployeeId(employeeId);

        double totalSalary = salaryStructure.calculateTotalSalary();
        double totalHoursWorked = attendanceList.stream()
                .mapToDouble(EmployeeAttendance::getHoursWorked)
                .sum();

        // Assume overtime is calculated if hours worked exceed a standard (e.g., 160 hours/month)
        double overtimeHours = Math.max(totalHoursWorked - 160, 0);
        double overtimePay = overtimeHours * (salaryStructure.getBasicSalary() / 160); // Assuming basicSalary is monthly

        // Calculate deductions (if any)
        double deductions = calculateDeductions(employeeId);

        // Create Payroll record
        Payroll payroll = new Payroll();
        payroll.setEmployee(salaryStructure.getEmployee());
        payroll.setPayrollDate(LocalDate.now());
        payroll.setTotalSalary(totalSalary);
        payroll.setOvertime(overtimePay);
        payroll.setDeductions(deductions);
        payroll.setNetSalary(totalSalary + overtimePay - deductions);

        return payrollRepository.save(payroll);
    }

    private double calculateDeductions(Long employeeId) {
        // Implement your logic for calculating deductions
        return 0.0; // Placeholder
    }
}
