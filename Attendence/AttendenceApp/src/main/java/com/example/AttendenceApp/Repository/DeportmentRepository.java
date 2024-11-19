package com.example.AttendenceApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Department;

@Repository
public interface DeportmentRepository  extends JpaRepository<Department, Long>{

}
