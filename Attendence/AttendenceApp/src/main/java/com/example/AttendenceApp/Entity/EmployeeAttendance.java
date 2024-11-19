package com.example.AttendenceApp.Entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeAttendance {

   

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "employee_id",referencedColumnName = "employeeId") // Ensure this matches your database schema
	    private Employee employee;

	    private LocalDate attendanceDate;  // New field to track attendance by date
	    private LocalDateTime loginTime;
	    private LocalDateTime logoutTime;// Assuming Double to handle decimal values  
	    

	    public long getHoursWorked() {
	        if (loginTime != null && logoutTime != null) {
	            Duration duration = Duration.between(loginTime, logoutTime);
	            return duration.toHours();
	        }
	        return 0; // or handle this according to your business logic
	    }
	    
	    public EmployeeAttendance(Employee employee, LocalDate attendanceDate, LocalDateTime loginTime) {
	        this.employee = employee;
	        this.attendanceDate = attendanceDate;
	        this.loginTime = loginTime;
	    }
	    
	    @Override
	    public String toString() {
	        return "EmployeeAttendance{" +
	                "id=" + id +
	                ", employee=" + (employee != null ? employee.getEmployeeId() : "null") +  // avoid deep printing
	                ", attendanceDate=" + attendanceDate +
	                ", loginTime=" + loginTime +
	                ", logoutTime=" + logoutTime +
	                '}';
	    }
}

