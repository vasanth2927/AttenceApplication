<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance History</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .sidebar {
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 200px;
            background-color: #007bff;
            padding: 15px;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
        }
        .sidebar a, .sidebar button {
            display: block;
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            color: #fff;
            background-color: #007bff;
            border: none;
            text-align: left;
            text-decoration: none;
            font-size: 16px;
        }
        .sidebar button {
            cursor: pointer;
        }
        .sidebar a:hover, .sidebar button:hover {
            background-color: #0056b3;
        }
        .content {
            margin-left: 220px; /* Space for sidebar */
            padding: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <a href="#">Dashboard</a>
    <form action="${pageContext.request.contextPath}/photo/scanemployee" method="get">
        <button type="submit">Mark Attendance</button>
    </form>
    <a href="${pageContext.request.contextPath}/attendance/history">View Attendance History</a>
    <a href="${pageContext.request.contextPath}/applyLeave">Apply for Leave</a>
    <a href="${pageContext.request.contextPath}/shift/schedule">Shift Schedule</a>
    <a href="${pageContext.request.contextPath}/api/logout">Logout</a>
</div>

<div class="content container">
    <h3 class="text-center">Attendance History</h3>
    
    <!-- Button to download PDF -->
    <form action="${pageContext.request.contextPath}/attendance/downloadAttendanceHistory" method="get" class="mb-3 text-center">
        <button type="submit" class="btn btn-primary">
            <i class="fas fa-download"></i> Download PDF
        </button>
    </form>
    
    <c:if test="${not empty attendanceRecords}">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>Date</th>
                    <th>Login Time</th>
                    <th>Logout Time</th>
                    <th>Hours Worked</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="attendance" items="${attendanceRecords}">
                    <tr>
                        <td>${attendance.attendanceDate}</td>
                        <td>${attendance.loginTime != null ? attendance.loginTime : 'Not Marked'}</td>
                        <td>${attendance.logoutTime != null ? attendance.logoutTime : 'Not Marked'}</td>
                        <td>${attendance.getHoursWorked()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    
    <c:if test="${empty attendanceRecords}">
        <p class="text-center">No attendance records found. <a href="${pageContext.request.contextPath}/attendance">Go back to Attendance</a>.</p>
    </c:if>
</div>

<!-- Add Font Awesome for icons -->
<script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
<!-- Add jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
