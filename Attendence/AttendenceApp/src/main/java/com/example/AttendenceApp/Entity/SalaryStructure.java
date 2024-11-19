package com.example.AttendenceApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryStructure {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "employee_id")
	    private Employee employee;

	    private double basicSalary;
	    private double housingAllowance;
	    private double transportAllowance;

	    public double calculateTotalSalary() {
	        return basicSalary + housingAllowance + transportAllowance;
	    }
}
