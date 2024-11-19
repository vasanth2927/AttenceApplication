<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Shift Allocation</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        // Frontend validation for employee ID
        function validateForm() {
            var employeeId = document.querySelector("input[name='employeeId']").value;
            if (isNaN(employeeId) || employeeId.trim() === "") {
                alert("Please enter a valid Employee ID.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission
        }
    </script>
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
    <h1>Allocate Shift to Employee</h1>
    <form action="/shift/allocate" method="post" onsubmit="return validateForm();">
        Employee ID: <input type="text" name="employeeId" value="${employeeId}" readonly/><br/>

        Shift Name: 
        <select name="shiftName">
            <option value="Morning Shift">Morning Shift</option>
            <option value="Afternoon Shift">Afternoon Shift</option>
            <option value="Night Shift">Night Shift</option>
        </select>
        <br/>

        Start Time: <input type="time" name="startTime"/><br/>
        End Time: <input type="time" name="endTime"/><br/>

        Start Date: <input type="date" name="startDate"/><br/>
        End Date: <input type="date" name="endDate"/><br/>

        <input type="submit" value="Allocate" class="btn btn-primary"/>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
