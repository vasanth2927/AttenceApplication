package com.example.AttendenceApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AttendenceApp.Entity.Leave;
import com.example.AttendenceApp.Repository.LeaveRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LeaveService {
	
	 @Autowired
	    private LeaveRepository leaveRepository;

	    // Apply for leave
	    public Leave applyLeave(Leave leave) {
	    	leave.setEmployeeName(leave.getEmployeeName());
	    	leave.setEndDate(leave.getEndDate());
	    	leave.setStartDate(leave.getStartDate());
	    	leave.setReason(leave.getReason());
	    	leave.setLeaveType(leave.getLeaveType());
	    	leave.setStatus("Pending");
	        return leaveRepository.save(leave);
	    }

	    // Get all leave applications (for admin view)
	    public List<Leave> getAllLeaves() {
	        return leaveRepository.findAll();
	    }

	    // Update leave status (approve/reject by admin)
	    public Leave updateLeaveStatus(Long id, String status) {
	        Leave leave = leaveRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid leave ID"));
	        leave.setStatus(status);
	        return leaveRepository.save(leave);
	    }
	    
	    public long countPendingLeaves() {
	        return leaveRepository.countByStatus("Pending");
	    }
	    
	    public List<Leave> getPendingLeaves() {
	        return leaveRepository.findByStatus("Pending");
	    }


}
