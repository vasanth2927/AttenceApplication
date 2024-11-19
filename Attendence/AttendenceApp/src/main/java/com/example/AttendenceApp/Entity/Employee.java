package com.example.AttendenceApp.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String username;
    private String email;
    private String password; // Encrypted password
    private String phone;
    
    @Lob
    @Column(name = "biometricdata", columnDefinition = "LONGTEXT")
    private String biometricData;  // Base64-encoded image data
    
    
    
    private String role;
    
    
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore      
    private Department department;
    
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EmployeeAttendance> attendances; // Automatically removes attendances when employee is deleted

    @OneToMany(mappedBy = "employee", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Shift> shifts; // Automatically removes shifts when employee is deleted
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + username + '\'' +
                // other fields you want to include
                '}';
    }
    
}
    
    

