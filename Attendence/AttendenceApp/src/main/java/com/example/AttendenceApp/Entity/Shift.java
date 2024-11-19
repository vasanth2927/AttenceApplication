	package com.example.AttendenceApp.Entity;



import java.time.LocalDate;
import java.time.LocalTime;

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
public class Shift {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long shiftId;
	    
	    @ManyToOne
	    @JoinColumn(name = "employee_id", nullable = false)
	    private Employee employee;

	    private String shiftName;
	    private LocalTime startTime;
	    private LocalTime endTime;
	    
	    // Add both start and end dates for the shift
	    private LocalDate startDate;
	    private LocalDate endDate;
}
