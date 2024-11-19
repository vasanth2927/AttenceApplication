<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance Report</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #343a40;
            padding-top: 20px;
            color: white;
        }
        .sidebar a {
            padding: 10px 15px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
        }
        .sidebar a:hover {
            background-color: #495057;
            color: white;
        }
        .content {
            margin-left: 260px;
            padding: 20px;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2 class="text-center">Inovace Technologies</h2>
    <a href="#">Dashboard</a>
    <a href="/api/employeedetails">Employees Details</a>
    <a href="/api/attendance-report?date=<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">Attendance Report</a>
    <a href="/admin/leaves">Leave Report <span class="badge badge-warning">
    <%= (request.getAttribute("pendingLeaveCount") != null ? request.getAttribute("pendingLeaveCount") : 0) %>
    </span></a>
    <a href="/shift/admin">Shift Management</a>
    <a href="/api/logout">Logout</a>
</div>

<div class="content">
    <div class="container">
        <h3>Attendance Report for <%= request.getAttribute("attendanceDate") %></h3>
        <c:if test="${not empty attendanceRecords}">
            <table class="table">
                <thead>
                    <tr>
                        <th>EmployeeID</th>
                        <th>Employee Name</th>
                        <th>Login Time</th>
                        <th>Logout Time</th>
                        <th>Hours Worked</th>
                        <th>Overtime Hours</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="attendance" items="${attendanceRecords}">
                        <tr>
                            <td>${attendance.employee.employeeId}</td>
                            <td>${attendance.employee.username}</td>
                            <td>${attendance.loginTime != null ? attendance.loginTime : 'Not Marked'}</td>
                            <td>${attendance.logoutTime != null ? attendance.logoutTime : 'Not Marked'}</td>
                            <td>${attendance.getHoursWorked()}</td>
                            <td>
                                <c:set var="hoursWorked" value="${attendance.getHoursWorked()}" />
                                <c:set var="overtimeHours" value="${hoursWorked - 8}" />
                                <c:choose>
                                    <c:when test="${overtimeHours > 0}">
                                        ${overtimeHours}
                                    </c:when>
                                    <c:otherwise>
                                        0
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty attendanceRecords}">
            <p>No attendance records found for <%= request.getAttribute("attendanceDate") %>.</p>
        </c:if>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
