<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shift Schedule</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .no-shifts {
            text-align: center;
            color: red;
            font-weight: bold;
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
    <h1>Your Shift Schedule</h1>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Shift Name</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty shifts}">
                <c:forEach var="shift" items="${shifts}">
                    <tr>
                        <td>${shift.shiftName}</td>
                        <td>${shift.startTime}</td>
                        <td>${shift.endTime}</td>
                        <td>${shift.startDate}</td>
                        <td>${shift.endDate}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty shifts}">
                <tr>
                    <td colspan="5" class="no-shifts">No shifts allocated yet.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</div>

<!-- Add jQuery and Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
