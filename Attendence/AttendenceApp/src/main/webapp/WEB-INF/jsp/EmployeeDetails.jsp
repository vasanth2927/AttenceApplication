<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.AttendenceApp.Entity.Employee" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .sidebar {
            height: 100%;
            width: 250px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #1C2331;
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
            background-color: #575D6E;
            color: white;
        }
        .content {
            margin-left: 260px;
            padding: 20px;
        }
        .table-header {
            background-color: #00B074;
            color: white;
        }
        .btn-sm {
            padding: 5px 10px;
        }
        .btn-update {
            background-color: #FFC107;
            border-color: #FFC107;
        }
        .btn-delete {
            background-color: #DC3545;
            border-color: #DC3545;
        }
        img {
            border-radius: 50%; /* Makes the photo circular */
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
    <h3 class="text-center">Employee Details</h3>
    <div class="text-center mb-3">
        <a href="/api/addemployee" class="btn btn-primary">Add Employee</a>
    </div>
    <table class="table table-bordered mt-3">
        <thead class="table-header">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Department</th>
            <th>Photo</th> <!-- New column for photo -->
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="employee" items="${employeeList}">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.username}</td>
                <td>${employee.email}</td>
                <td>${employee.phone}</td>
                <td>${employee.role}</td>
                <td>${employee.department.departmentName}</td>
                

<td>
<img src="/images/empicon.png" alt="EmployeePhoto" width="150" height="150" />

</td>
                
                <td>
                    <form action="/api/updateemployeedata?id=${employee.employeeId}" method="post" style="display:inline;">
                        <input type="hidden" name="employeeId" value="${employee.employeeId}">
                        <button type="submit" class="btn btn-update btn-sm">Update</button>
                    </form>
                    
                    <form action="/api/deleteemployee?id=${employee.employeeId}" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this employee?');">
                        <input type="hidden" name="employeeId" value="${employee.employeeId}">
                        <button type="submit" class="btn btn-delete btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
