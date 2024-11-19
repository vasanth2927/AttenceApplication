<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.AttendenceApp.Entity.Employee" %>
<%@ page import="com.example.AttendenceApp.Entity.Shift" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shift Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .sidebar {
            width: 200px;
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .sidebar a {
            display: block;
            color: black;
            padding: 16px;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #ddd;
        }
        .container {
            margin-left: 220px;
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

<div class="container mt-4">
    <h2>Employee Shift Management</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Role</th>
                <th>Department</th>
                <th>Allocate Shift</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Employee> employees = (List<Employee>) request.getAttribute("employees");
                if (employees != null) {
                    for (Employee employee : employees) {
                        String departmentName = employee.getDepartment() != null ? employee.getDepartment().getDepartmentName() : "N/A"; // Default value
            %>
            <tr>
                <td><%= employee.getEmployeeId() %></td>
                <td><%= employee.getUsername() %></td>
                <td><%= employee.getEmail() %></td>
                <td><%= employee.getRole() %></td>
                <td><%= departmentName %></td>
                <td>
                    <form action="/shift/allocateShift" method="get">
                        <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">
                        <button type="submit" class="btn btn-primary">Allocate Shift</button>
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" class="text-center">No employees found.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
