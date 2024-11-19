package com.example.AttendenceApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.AttendenceApp.Entity.Department;
import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class Employeecontroller {
	
	@Autowired
	EmployeeService empservice;
	
	@GetMapping("/listemployee")
	public Iterable<Employee> listemployee() {
	return 	empservice.listemployee();
		
	}

	
	@GetMapping("/listdept")
	public List<Department> listdept() {
	return 	empservice.listdept();
		
	}
	
	@PostMapping("/addemployee")
	public String addemployee(@RequestBody Employee employee) {
	return 	empservice.addemployee(employee);
		
	}
	
	
	
	@GetMapping("/Attendence")
	public ModelAndView MarkAttendence() {
        ModelAndView modelAndView = new ModelAndView();
  modelAndView.setViewName("Attendence");
  
  return modelAndView;
		
	}
	
//	@PostMapping("/checkin")
//    public ModelAndView checkIn(@RequestParam Long employeeId) {
//		empservice.checkIn(employeeId);
//        ModelAndView modelAndView = new ModelAndView("mark-attendance");
//        modelAndView.addObject("status", "Checked In");
//        return modelAndView;
//    }
//	@PostMapping("/checkout")
//    public ModelAndView checkOut(@RequestParam Long employeeId) {
//		empservice.checkOut(employeeId);
//        ModelAndView modelAndView = new ModelAndView("mark-attendance");
//        modelAndView.addObject("status", "Checked Out");
//        return modelAndView;
//    }

}
