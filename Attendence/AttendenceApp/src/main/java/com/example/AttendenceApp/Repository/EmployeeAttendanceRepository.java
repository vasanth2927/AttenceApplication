package com.example.AttendenceApp.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AttendenceApp.Entity.Employee;
import com.example.AttendenceApp.Entity.EmployeeAttendance;


@Repository
public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    Optional<EmployeeAttendance> findByEmployeeAndAttendanceDate(Employee employee, LocalDate attendanceDate);

    List<EmployeeAttendance> findByEmployee_EmployeeId(Long employeeId); // Use 'employee.employeeId'

	List<EmployeeAttendance> findByAttendanceDate(LocalDate date);
	
 //   List<EmployeeAttendance> findByEmployeeIdOrderByDateAsc(Long employeeId);

    List<EmployeeAttendance> findByEmployee_EmployeeIdOrderByAttendanceDateAsc(Long employeeId);



	
}
