package com.example.AttendenceApp.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long payrollId;

	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private Employee employee;

	    private LocalDate payrollDate; // Date of payroll processing
	    private double totalSalary;      // Base salary + allowances
	    private double overtime;         // Overtime hours or amount
	    private double bonus;            // Any bonuses awarded
	    private double deductions;       // Total deductions
	    private double netSalary;        // Final amount to be paid
}
