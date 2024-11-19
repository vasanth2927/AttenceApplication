package com.example.AttendenceApp.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections; // Use java.util.Collections for sorting


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.AttendenceApp.Bean.LoginRequest;
import com.example.AttendenceApp.Entity.Department;
import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.EmployeeAttendance;
import com.example.AttendenceApp.Service.Attendenceservice;
import com.example.AttendenceApp.Service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@CrossOrigin()
@RequestMapping("/api")
public class AttendenceController {
	
	
	
//	@Autowired
//	private MyUserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	EmployeeService empservice;
//	@Autowired
//	private AuthenticationManager authenticationManager;

	@Autowired
	Attendenceservice attendenceService;

//	@Autowired
//	Jwtutil jwtutil;

	@PostMapping("/login")
	public ModelAndView login(@RequestBody LoginRequest loginRequest, HttpSession session) throws Exception {
	    // Authenticate the user and determine which view to show based on the result
	    String viewName = attendenceService.authenticateAndRedirect(loginRequest);

	    ModelAndView modelAndView = new ModelAndView();
System.out.println("Attendencecontroller");
	    if ("deshbord".equals(viewName)) {
	        modelAndView.setViewName("deshbord");
	    } 
	    else if ("Employee".equals(viewName)) {
	        String username = attendenceService.getAuthenticatedUsername(loginRequest);
	        session.setAttribute("loggedInUsername", username);
	        
	        modelAndView.setViewName("Employee");
	        modelAndView.addObject("username", username);
	    } else {
	        modelAndView.setViewName("index");
	        modelAndView.addObject("error", "Invalid credentials. Please try again.");
	    }

	    return modelAndView;
	}

	@GetMapping("/logout")
	public ModelAndView logout() {
		
		return new ModelAndView("index");
		
	}
	
	
	@GetMapping("/employeedetails")
	public ModelAndView employyeedetails() {
		ModelAndView modelAndView = new ModelAndView();
		Iterable<Employee> employees = empservice.getAllEmployees(); // Fetch employee data
		modelAndView.addObject("employeeList", employees); // Add data to model
		modelAndView.setViewName("EmployeeDetails"); // Set the view name
		return modelAndView;
	}

	@GetMapping("/addemployee")
	public ModelAndView addemployee() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Addemployee"); // Set the view name
		return modelAndView;
	}

	@PostMapping("/addemployeedata")
	public String addEmployeeData1(
	        @RequestParam("username") String username, 
	        @RequestParam("email") String email,
	        @RequestParam("phone") String phone, 
	        @RequestParam("role") String role,
	        @RequestParam("password") String password,
	        @RequestParam("departmentId") Long departmentId, 
	        @RequestParam("biometricData") String biometricData, 
	        RedirectAttributes redirectAttributes) {

	    // Retrieve the department from the service layer
	    Department department = attendenceService.findById(departmentId);

	    if (department != null) {
	        // Create a new employee instance
	        Employee employee = new Employee();
	        employee.setUsername(username);
	        employee.setEmail(email);
	        employee.setPhone(phone);
	        employee.setRole(role);
	        employee.setPassword(password); // Store password as is
	        employee.setDepartment(department);
	        employee.setBiometricData(biometricData);

	        // Save the employee to the database
	        attendenceService.addEmployeedata(employee);

	        // Add success message to RedirectAttributes
	        redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully!");
	        return "redirect:/api/employeedetails"; // Redirect after successful addition
	    } else {
	        // Handle invalid department scenario
	        redirectAttributes.addFlashAttribute("errorMessage", "Invalid department selected.");
	        return "redirect:/api/addemployee"; // Redirect back to the add employee page
	    }
	}



	@PostMapping("/updateemployeedata")
	public ModelAndView updateemployeedata(@RequestParam("employeeId") Long employeeId) {
		Employee emp = empservice.updateemployeeId(employeeId).get();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("employee", emp); // Add data to model
		modelAndView.setViewName("updateemployee"); // Set the view name
		return modelAndView;

	}

	@PostMapping("/updataadd")
	public String updatedataadd(@RequestParam Long employeeId, 
	                            @RequestParam String username,
	                            @RequestParam String email, 
	                            @RequestParam String phone, 
	                            @RequestParam String role,
	                            @RequestParam(required = false) String password, 
	                            @RequestParam Long departmentId,
	                            @RequestParam(required = false) String biometricData,  // New parameter for photo
	                            RedirectAttributes redirectAttributes) {

	    // Fetch the existing employee from the database using employeeId
	    Employee existingEmployee = empservice.findEmployeeById(employeeId);

	    if (existingEmployee == null) {
	        redirectAttributes.addFlashAttribute("errorMessage", "Employee not found.");
	        return "redirect:/api/updateemployeedata";
	    }

	    // Update fields with new values
	    existingEmployee.setUsername(username);
	    existingEmployee.setEmail(email);
	    existingEmployee.setPhone(phone);
	    existingEmployee.setRole(role);

	    // Update password only if a new password is provided
	    if (password != null && !password.isEmpty()) {
	        // Encrypt the password before storing (if applicable in your logic)
	        existingEmployee.setPassword(password); 
	    }

	    // Update department
	    Department department = new Department();
	    department.setDepartmentId(departmentId);
	    existingEmployee.setDepartment(department);

	    // Update biometric data (photo) if provided
	    if (biometricData != null && !biometricData.isEmpty()) {
	        existingEmployee.setBiometricData(biometricData);
	    }

	    // Update the employee via service layer
	    String updateResult = empservice.updateEmployee(existingEmployee);

	    if ("EmployeeDetails".equals(updateResult)) {
	        // Successful update
	        redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully.");
	        return "redirect:/api/employeedetails";
	    } else {
	        // Update failed
	        redirectAttributes.addFlashAttribute("errorMessage", "Error updating employee.");
	        return "redirect:/api/updateemployeedata";
	    }
	}

	@PostMapping("/deleteemployee")
	public String deleteemployee(@RequestParam("employeeId") Long employeeId, RedirectAttributes redirectAttributes) {
		 empservice.deleteemployee(employeeId);
		redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully.");
		return "redirect:/api/employeedetails";

	}
	
	@GetMapping("/attendance-report")
	public String getAttendanceReport(@RequestParam(value = "date", required = false) String dateStr, Model model) {
	    LocalDate attendanceDate;
	    if (dateStr == null) {
	        attendanceDate = LocalDate.now(); // Default to today's date
	    } else {
	        attendanceDate = LocalDate.parse(dateStr); // Parse the provided date
	    }
	    
	    List<EmployeeAttendance> attendanceRecords = attendenceService.findByAttendanceDate(attendanceDate);
	    model.addAttribute("attendanceDate", attendanceDate);
	    model.addAttribute("attendanceRecords", attendanceRecords);
	    
	    return "AttendenceReport"; // Make sure this matches your JSP file name
	}

}

//
//	private void authenticate(String username, String password) throws Exception {
//		Objects.requireNonNull(username);
//		Objects.requireNonNull(password);
//
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
