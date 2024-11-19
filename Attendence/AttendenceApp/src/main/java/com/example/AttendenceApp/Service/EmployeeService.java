package com.example.AttendenceApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AttendenceApp.Entity.Department;
import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Repository.DeportmentRepository;
import com.example.AttendenceApp.Repository.EmployeeAttendanceRepository;
import com.example.AttendenceApp.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository emprep;

	@Autowired
	DeportmentRepository deptRepository;

	@Autowired
	EmployeeAttendanceRepository attendencerep;

	public Iterable<Employee> getAllEmployees() {
		return emprep.findAll(); // Fetch all employees from database
	}

	public Iterable<Employee> listemployee() {
		return emprep.findAll();

	}

	public List<Department> listdept() {
		return deptRepository.findAll();

	}

	public String addemployee(Employee employee) {
		if (employee != null) {
			emprep.save(employee);
			return "emp add success fulyyy.....";
		} else {
			return "employee value is null";
		}

	}

	public Optional<Employee> updateemployeeId(Long employeeId) {

		Optional<Employee> emp = emprep.findById(employeeId);
		return emp;

	}

	 public String updateEmployee(Employee employee) {
	        if (employee.getEmployeeId() != null && emprep.existsById(employee.getEmployeeId())) {
	            emprep.save(employee);
	            return "EmployeeDetails";
	        } else {
	            return "updateemployee";
	        }
	    }

	public void deleteemployee(Long employeeId) {
		      emprep.deleteById(employeeId);          
	}

	public Employee findEmployeeById(Long employeeId) {
		Employee employee=emprep.findById(employeeId).get();
		
		return employee;
	}

	public Long getEmployeeIdByUsername(String username) {
        Employee employee = emprep.findByUsername(username);
        return employee != null ? employee.getEmployeeId() : null;
    }


}
