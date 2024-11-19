<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Apply for Leave</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .sidebar {
            width: 200px;
            padding: 15px;
            background-color: #1C2331;
            color: white;
            position: fixed;
            height: 100%;
        }
        .sidebar a, .sidebar button {
            display: block;
            padding: 10px;
            margin: 5px 0;
            color: #fff;
            background-color: #007bff;
            border: none;
            text-align: left;
            text-decoration: none;
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

<div class="content">
    <h2>Apply for Leave</h2>
    <form action="/submitLeave" method="post">
        <!-- Hidden field to store the employee's name -->
        <input type="hidden" name="employeeName" value="${employeeName}" />
        
        <div class="form-group">
            <label for="leaveType">Leave Type:</label>
            <select name="leaveType" class="form-control" required>
                <option value="Sick Leave">Sick Leave</option>
                <option value="Casual Leave">Casual Leave</option>
                <option value="Paid Leave">Paid Leave</option>
            </select>
        </div>

        <div class="form-group">
            <label for="startDate">Start Date:</label>
            <input type="date" name="startDate" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="endDate">End Date:</label>
            <input type="date" name="endDate" class="form-control" required />
        </div>

        <div class="form-group">
            <label for="reason">Reason:</label>
            <textarea name="reason" class="form-control" required></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Submit Leave</button>
    </form>
</div>

<!-- Add jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
