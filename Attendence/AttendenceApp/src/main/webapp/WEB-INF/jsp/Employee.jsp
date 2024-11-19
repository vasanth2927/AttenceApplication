<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Attendance Management</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f3f4f6;
        }
        .header {
            background-color: #4CAF50;
            padding: 20px;
            text-align: center;
            color: white;
            font-size: 2em;
        }
        .sidebar {
            background-color: #333;
            padding: 15px;
            height: 100vh;
            color: white;
            float: left;
            width: 20%;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 10px 0;
            font-size: 1.2em;
        }
        .sidebar a:hover {
            background-color: #575757;
        }
        .main-content {
            margin-left: 20%;
            padding: 20px;
            background-color: #fff;
            min-height: 100vh;
        }
        .main-content h2 {
            color: #333;
        }
        .card {
            background-color: #e7e7e7;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .card h3 {
            color: #333;
        }
        .footer {
            background-color: #4CAF50;
            text-align: center;
            color: white;
            padding: 10px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="header">
        Employee Attendance Management
    </div>

    <div class="sidebar">
        <a href="#">Dashboard</a>
        
        <form action="/photo/scanemployee" method="get">
            <button type="submit">Mark Attendance</button>
        </form>
        <a href="/attendance/history">View Attendance History</a>
        <a href="/applyLeave">Apply for Leave</a>
        
        <a href="/shift/schedule">Shift schedule</a>
        <a href="/api/logout">Logout</a>
    </div>

    <div class="main-content">
        <!-- Displaying the employee's username -->
        <h2>Welcome, <%= session.getAttribute("loggedInUsername") != null ? session.getAttribute("loggedInUsername") : "Employee" %>!</h2>
        <!-- Main content here -->
    </div>

    <div class="footer">
        &copy; 2024 Employee Attendance Management
    </div>

</body>
</html>
