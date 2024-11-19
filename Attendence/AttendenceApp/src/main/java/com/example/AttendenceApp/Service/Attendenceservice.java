
package com.example.AttendenceApp.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AttendenceApp.Bean.LoginRequest;
import com.example.AttendenceApp.Entity.Admin;
import com.example.AttendenceApp.Entity.Department;
import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.EmployeeAttendance;
import com.example.AttendenceApp.Repository.AdminRepository;
import com.example.AttendenceApp.Repository.DeportmentRepository;
import com.example.AttendenceApp.Repository.EmployeeAttendanceRepository;
import com.example.AttendenceApp.Repository.EmployeeRepository;

@Service
public class Attendenceservice {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DeportmentRepository departmentRepository;
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    EmployeeAttendanceRepository employeeAttendanceRepository;

//    @Autowired
//    private Jwtutil jwtUtil;

    public String authenticateAndRedirect(LoginRequest loginRequest) {
        if ("Admin".equals(loginRequest.getRole())) {
        	System.out.println(loginRequest.getUsername());
        	System.out.println(loginRequest.getPassword());
            Admin admin = adminRepository.findByusername(loginRequest.getUsername());
            if (admin != null && admin.getPassword().equals(loginRequest.getPassword())) {

      //          String token = jwtUtil.generateToken(admin.getUsername());
      //          System.out.println(token);

                return "deshbord";  
            } else {
                return "index"; 
            }
        } else if ("Employee".equals(loginRequest.getRole())) {
            Employee employee = employeeRepository.findByusername(loginRequest.getUsername());
            if (employee != null && employee.getPassword().equals(loginRequest.getPassword())) {
                
      //          String token = jwtUtil.generateToken(employee.getUsername());
                return "Employee";  
            } else {
                return "index";  
            }
        }

        return "login";  
    }
    
    
    public String getAuthenticatedUsername(LoginRequest loginRequest) {
        // Assume you have a method to authenticate the user and retrieve the username
        Employee user = employeeRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (user != null) {
            return user.getUsername();
        }
        throw new RuntimeException("User not found");
    }
    
    

	public void addEmployeedata(Employee employee) {
		employeeRepository.save(employee);
		
	}
	
	 public List<Department> getAllDepartments() {
	         List<Department>departments=departmentRepository.findAll();
	         System.out.println(departments);
	         return departments;
	    }

	 public Department findById(Long Id) {
	        return departmentRepository.findById(Id).orElse(null);
	    }
	
	 
	 
	 
	 
	 public List<EmployeeAttendance> findByAttendanceDate(LocalDate date) {
	        return employeeAttendanceRepository.findByAttendanceDate(date);
	    }
	
}
