package com.example.AttendenceApp.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByEmployeeId(Long employeeId); // Update parameter type to Long

	 
	public List<Employee> findAll();
	
	public	Employee findByusername(String username);
	
	
	
    public	Employee findBypassword(String password);



    Employee findByUsername(String username);
	



	public Employee findByUsernameAndPassword(String username, String password);





}
