package com.example.AttendenceApp.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.Leave;
import com.example.AttendenceApp.Repository.EmployeeRepository;
import com.example.AttendenceApp.Service.LeaveService;
import com.example.AttendenceApp.Service.NotificationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
@Controller
public class LeaveController {
	
	@Autowired
    private LeaveService leaveService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	EmployeeRepository emprep;

	  @GetMapping("/applyLeave")
	    public ModelAndView showLeaveForm(HttpSession session) {
	        String employeeName = (String) session.getAttribute("loggedInUsername");
	        ModelAndView modelAndView = new ModelAndView("apply-leave");
	        modelAndView.addObject("employeeName", employeeName);
	        return modelAndView;
	    }

	    // Handle leave application submission
	    @PostMapping("/submitLeave")
	    public String submitLeave(@RequestParam("employeeName") String employeeName,
	                              @RequestParam("leaveType") String leaveType,
	                              @RequestParam("startDate") String startDate,
	                              @RequestParam("endDate") String endDate,
	                              @RequestParam("reason") String reason,
	                              Model model) {
	    	
	    	
	        Leave leave = new Leave(employeeName,  LocalDate.parse(endDate),leaveType,reason, LocalDate.parse(startDate) );
	        leaveService.applyLeave(leave);
	        model.addAttribute("message", "Leave application submitted successfully!");
	        return "Employee"; // Or any other view you want to return after submission
	    }

    @GetMapping("/admin/leaves")
    public String viewAllLeaves(Model model) {
        List<Leave> leaves = leaveService.getAllLeaves();
        model.addAttribute("leaves", leaves);
        return "admin-leave-requests"; // Show all leave requests to admin
    }

    @PostMapping("/admin/leave/{id}/update")
    public String updateLeaveStatus(@PathVariable("id") Long id,
                                    @RequestParam("status") String status) {
        // Update leave status in the system
        Leave leave = leaveService.updateLeaveStatus(id, status);
        
        // Fetch employee details based on the leave
        Employee empdetails = emprep.findByusername(leave.getEmployeeName());
        if (empdetails != null) {
            String employeeEmail = empdetails.getEmail(); // Fetch employee's email
            notificationService.sendNotification(employeeEmail, status);  // Send email notification
        }
        
        return "redirect:/admin/leaves";  // Redirect to the list of leave requests
    }

    @GetMapping("/adminDashboard")
    public String showAdminDashboard(Model model) {
        // Fetch the count of pending leave requests
        long pendingLeaveCount = leaveService.countPendingLeaves();
        model.addAttribute("pendingLeaveCount", pendingLeaveCount);

        return "admin-dashboard";  // The JSP page
    }
    
    
    
    @PostMapping("/approveLeave")
    public String approveLeave(@RequestParam("leaveId") Long leaveId) {
        leaveService.updateLeaveStatus(leaveId, "Approved");
        return "redirect:/viewNotifications";  // Redirect back to notifications page
    }

    @PostMapping("/rejectLeave")
    public String rejectLeave(@RequestParam("leaveId") Long leaveId) {
        leaveService.updateLeaveStatus(leaveId, "Rejected");
        return "redirect:/viewNotifications";  // Redirect back to notifications page
    }

    @GetMapping("/viewNotifications")
    public String viewNotifications(Model model) {
        List<Leave> pendingLeaves = leaveService.getPendingLeaves();
        model.addAttribute("pendingLeaves", pendingLeaves);

        return "notifications";  // New JSP page for notifications
    }

    
    
    

    
    
}
