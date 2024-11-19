package com.example.AttendenceApp.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.Shift;
import com.example.AttendenceApp.Repository.EmployeeRepository;
import com.example.AttendenceApp.Service.ShiftService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;
    
    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/admin")
    public String showShiftManagementPage(Model model) {
        model.addAttribute("employees", shiftService.getAllEmployees());
        return "ShiftAdmin"; // Name of your JSP file without .jsp
    }
    
    @GetMapping("/allocateShift")
    public String allocateShift(@RequestParam("employeeId") Long employeeId, Model model) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        
        if (employee == null) {
            model.addAttribute("error", "Employee not found.");
            return "shiftManagement"; // Return to the main shift management page
        }
        
        model.addAttribute("employeeId", employee.getEmployeeId());
        model.addAttribute("employeeName", employee.getUsername()); // Assuming getUsername() returns the employee's name
        return "AllocateShift"; // Ensure this matches your JSP file name
    }



    @PostMapping("/allocate")
    public String allocateShift(
            @RequestParam String employeeId,
            @RequestParam String shiftName,
            @RequestParam String startTime,
            @RequestParam String endTime,
            @RequestParam String startDate,
            @RequestParam String endDate,
            RedirectAttributes redirectAttributes) {

        // Log the employee ID
        System.out.println("Attempting to allocate shift to employee ID: " + employeeId);

        // Sanitize employeeId
        String sanitizedEmployeeId = employeeId.replaceAll(",", ""); // Remove any commas

        try {
            Long empId = Long.parseLong(sanitizedEmployeeId);
            LocalTime startLocalTime = LocalTime.parse(startTime);
            LocalTime endLocalTime = LocalTime.parse(endTime);
            LocalDate startLocalDate = LocalDate.parse(startDate);
            LocalDate endLocalDate = LocalDate.parse(endDate);

            shiftService.allocateShiftToEmployee(empId, shiftName, startLocalTime, endLocalTime, startLocalDate, endLocalDate);

            redirectAttributes.addFlashAttribute("successMessage", "Shift allocated successfully!");
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid Employee ID format.");
            return "redirect:/shift/allocateShift"; // Redirect back to the allocation page
        }

        return "redirect:/shift/admin"; 
    }


    
    

    @GetMapping("/schedule")
    public String getShiftSchedule(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedInUsername");

        if (username == null) {
            return "redirect:/login";  // Redirect to login if employee is not logged in
        }

        Employee employee = employeeRepository.findByUsername(username);
        
        if (employee != null) {
            List<Shift> shifts = shiftService.getShiftsForEmployee(employee.getEmployeeId());
            model.addAttribute("shifts", shifts);
            
            // Check if shifts are empty
            if (shifts.isEmpty()) {
                model.addAttribute("message", "No shifts allocated yet.");
            }
        } else {
            model.addAttribute("error", "Employee not found.");
        }

        return "shiftSchedule";  // Return the shift schedule view
    }


}
