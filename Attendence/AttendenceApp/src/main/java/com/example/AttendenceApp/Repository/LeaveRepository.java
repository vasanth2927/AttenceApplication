package com.example.AttendenceApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>{

	long countByStatus(String status);
	
	List<Leave> findByStatus(String status);
	
	
}
