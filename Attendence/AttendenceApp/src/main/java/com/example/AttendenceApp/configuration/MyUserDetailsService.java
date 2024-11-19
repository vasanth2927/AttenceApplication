//package com.example.AttendenceApp.configuration;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.AttendenceApp.Entity.Employee;
//import com.example.AttendenceApp.Repository.EmployeeRepository;
//
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private EmployeeRepository userRepository;
//
//    
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	Employee employee = userRepository.findByUsername(username);
//        if (employee == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        System.out.println(employee.getPassword());
//        System.out.println(employee.getUsername());
//        return new User(employee.getUsername(), employee.getPassword(),
//				new ArrayList<>());
//    }
//}
