package com.example.AttendenceApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.SalaryStructure;


@Repository
public interface SalaryStructureRepository extends JpaRepository<SalaryStructure,Long>{

    SalaryStructure findByEmployee_EmployeeId(Long employeeId);

}
