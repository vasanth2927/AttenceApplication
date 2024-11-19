package com.example.AttendenceApp.Controller;

import com.example.AttendenceApp.Entity.Payroll;
import com.example.AttendenceApp.Service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @PostMapping("/calculate/{employeeId}")
    public ResponseEntity<Payroll> calculatePayroll(@PathVariable Long employeeId) {
        Payroll payroll = payrollService.calculatePayroll(employeeId);
        return ResponseEntity.ok(payroll);
    }
}
