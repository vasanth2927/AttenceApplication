package com.example.AttendenceApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AttendenceApp.Entity.EmployeeAttendance;
import com.example.AttendenceApp.Service.AttendanceHistoryService;
import com.example.AttendenceApp.Service.EmployeeService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/attendance")
public class AttendanceHistoryController {

    @Autowired
    private AttendanceHistoryService attendanceHistoryService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/history")
    public String getAttendanceHistory(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedInUsername"); // Use the same key
        System.out.println("Username: " + username);
        
       
        System.out.println("hiiii");

                System.out.println("hiiii 1");


        // Check if username is null
        if (username == null) {
            System.out.println("Username is null. User may not be logged in.");
            return "deshbord";
        }

        // Fetch employee ID using the username
        Long employeeId = employeeService.getEmployeeIdByUsername(username);
        System.out.println("Employee ID: " + employeeId);

        // Fetch attendance history using the employee ID
        List<EmployeeAttendance> attendanceHistory = attendanceHistoryService.getAttendanceHistoryByEmployeeId(employeeId);
        System.out.println("Attendance History: " + attendanceHistory);

        // Add the attendance history to the model
        model.addAttribute("attendanceRecords", attendanceHistory);
        
        return "attendanceHistory"; // Name of your JSP file
    }

    


@GetMapping("/downloadAttendanceHistory")
public void downloadAttendanceHistory(HttpSession session, HttpServletResponse response) throws Exception {
    String username = (String) session.getAttribute("loggedInUsername");
    
    if (username == null) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
        return;
    }

    Long employeeId = employeeService.getEmployeeIdByUsername(username);
    List<EmployeeAttendance> attendanceHistory = attendanceHistoryService.getAttendanceHistoryByEmployeeId(employeeId);

    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition", "attachment; filename=attendance_history.pdf");

    // Create PDF document
    PdfWriter writer = new PdfWriter(response.getOutputStream());
    PdfDocument pdfDocument = new PdfDocument(writer);
    Document document = new Document(pdfDocument);

    document.add(new Paragraph("Attendance History for " + username));

    // Create table
    Table table = new Table(4);
    table.addHeaderCell("Date");
    table.addHeaderCell("Login Time");
    table.addHeaderCell("Logout Time");
    table.addHeaderCell("Hours Worked");

    for (EmployeeAttendance attendance : attendanceHistory) {
        table.addCell(attendance.getAttendanceDate().toString());
        table.addCell(attendance.getLoginTime() != null ? attendance.getLoginTime().toString() : "Not Marked");
        table.addCell(attendance.getLogoutTime() != null ? attendance.getLogoutTime().toString() : "Not Marked");
        table.addCell(String.valueOf(attendance.getHoursWorked()));
    }

    document.add(table);
    document.close();
}

}
