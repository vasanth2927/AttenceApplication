package com.example.AttendenceApp.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
  // Specifying table name to avoid conflicts with 'leave' keywor
@Table(name = "leaves")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Leave {
    
    public Leave(String employeeName, LocalDate enDate, String leaveType, String reason, LocalDate starLocalDate) {
    	    this.employeeName = employeeName;
    	    this.endDate = enDate;
    	    this.leaveType = leaveType;
    	    this.reason = reason;
    	    this.startDate = starLocalDate;
    	    this.status = "Pending";
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private String status; 
}
